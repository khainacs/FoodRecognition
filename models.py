from pydantic import BaseModel
from uuid import UUID, uuid4
from typing import Optional
class ImageData(BaseModel):
    id: Optional[UUID] = uuid4
    name: str