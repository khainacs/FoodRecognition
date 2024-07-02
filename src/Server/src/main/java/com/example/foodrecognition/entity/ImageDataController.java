package com.example.foodrecognition.entity;

import com.example.foodrecognition.ImageUploader;
import com.example.foodrecognition.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/image")
public class ImageDataController {
    @Autowired
    private StorageService service;

    private static final String uploadDirectory = "C:/Users/khain/OneDrive/Pictures";

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file, uploadDirectory);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] image = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}