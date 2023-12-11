package com.example.foodrecognition.service;

import com.example.foodrecognition.entity.ImageData;
import com.example.foodrecognition.repository.StorageRepository;
import com.example.foodrecognition.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * this class is take repository for logic layer to upload UI
 */
@Service
public class StorageService {

    /*annotation help reduce dependency*/
    @Autowired
    private StorageRepository repository;

    /*MultipartFile is used to in controller spring boot when upload image, file*/
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder().name(file.getOriginalFilename())
                        .type(file.getContentType()).
                        imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (imageData != null){
            return "file upload successfully: "  + file.getOriginalFilename();
        }
        return null;

    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
