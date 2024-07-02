

import torch
import timm
import torchvision
from torch.cuda.amp import autocast

from PIL import Image

from ml import Model

classes_cfg = [
    'Banh beo', 'Banh bot loc', 'Banh can', 'Banh canh', 'Banh chung',
    'Banh cuon', 'Banh duc', 'Banh gio', 'Banh khot', 'Banh mi', 'Banh pia', 
    'Banh tet', 'Banh trang nuong', 'Banh xeo', 'Bun bo Hue', 'Bun dau mam tom', 
    'Bun mam', 'Bun rieu', 'Bun thit nuong', 'Ca kho to', 'Canh chua', 'Cao lau', 
    'Chao long', 'Com tam', 'Goi cuon', 'Hu tieu', 'Mi quang', 'Nem chua', 'Pho',
    'Xoi xeo'
]
class Classifier(object):
    def __init__(
        self
    ) -> None:
        self.model = self.load_model()
        self.transforms = self.define_trainsforms()

    def load_model(
        self
    ):
        model = Model(device='cpu', model_name='resnet18.a1_in1k', num_classes=30)
        model.load_state_dict(torch.load('weights.pth', map_location=torch.device('cpu')))
        model.eval()
        return model


    def infer(
        self,
        image_file: str
    ):
        image = Image.open(image_file)
        image = self.transforms(image)
        image = image.unsqueeze(0)
        with torch.no_grad():
            with autocast():
                preds = self.model(image)

            _, pred = torch.max(preds.data, dim=1)
        return classes_cfg[pred.cpu().item()]


    def define_trainsforms(
        self
    ):
        transforms = torchvision.transforms.Compose(
            [
                torchvision.transforms.Resize((224,224)),
                torchvision.transforms.ToTensor(),
                torchvision.transforms.Normalize(0.5, 0.5)
            ]
        )
        return transforms


# if __name__ == '__main__':
#     classifier = Classifier()
#     image_file = '326.jpg'
#     output = classifier.infer(image_file)
#     print(output)
