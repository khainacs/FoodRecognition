package com.example.foodrecognition.service;

import com.example.foodrecognition.ImageUploader;
import com.example.foodrecognition.entity.ImageData;
import com.example.foodrecognition.repository.StorageRepository;
import com.example.foodrecognition.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * this class is take repository for logic layer to upload UI
 */
@Service
public class StorageService {

    /*annotation help reduce dependency*/
    @Autowired
    private StorageRepository repository;

    @Autowired
    private ImageUploader imageUploader;
    /*MultipartFile is used to in controller spring boot when upload image, file*/
    public String uploadImage(MultipartFile file, String uploadDirectory) throws IOException {
        Path uploadPath = Path.of(uploadDirectory);

        //this file's directory from root to file's name
        Path filePath = uploadPath.resolve(file.getOriginalFilename());

        if(!Files.exists(filePath)){
            Files.createDirectories(filePath);
        }

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        //save path on db
        ImageData imageData = repository.save(ImageData.builder().name(file.getOriginalFilename())
                .path(uploadDirectory).build());

        return imageUploader.uploadOnFastApi(filePath.toString());

//        if (imageData != null){
//            return "file upload successfully: "  + file.getOriginalFilename();
//        }
//
//        return null;

    }

    public byte[] downloadImage(String fileName){
//        Optional<ImageData> dbImageData = repository.findByName(fileName);
//        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());``
//        return images;
        return null;
    }
}