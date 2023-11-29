from msilib import type_string
import shutil
from fastapi import FastAPI, Request, UploadFile, File
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from fastapi.staticfiles import StaticFiles
from typing import Annotated
from typing import List
from pydantic import BaseModel
from fastapi.responses import StreamingResponse
from PIL import Image


IMAGEDIR = "images/"
 
app = FastAPI()
templates = Jinja2Templates(directory="template")
app.mount("/images", StaticFiles(directory="images"), name="images")
 
@app.get('/', response_class=HTMLResponse)
def home(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})
 
@app.post("/upload")
def uploads( file = UploadFile(...)):
    try:
       
        with open(file.filename, 'wb') as f:
            shutil.copyfileobj(file.file, f)
    except Exception:
        return {"message": "There was an error uploading the file"}
    finally:
        file.close()
        return {"message": f"Successfully uploaded  {file.filename}"}
        
    
# @app.post("/upload")
# async def uploads(request: Request):
#     path = await request.body()
#     image = Image.open(path)
#     file = UploadFile(image)
#     print(path)
#     return {"message": f"Successfully uploaded" }
    