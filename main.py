import shutil
from fastapi import FastAPI, Request
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
 
class Item(BaseModel):
    name: str
    description: str | None = None
    price: float
    tax: float | None = None
    tags: list[str] = []
    
@app.post("/upload")
async def uploads(path: Request):
    return {"name": "Successfully!!!"}
   
    
    