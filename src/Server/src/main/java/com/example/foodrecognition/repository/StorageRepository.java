package com.example.foodrecognition.repository;

import com.example.foodrecognition.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.awt.*;
import java.util.Optional;

/**
 * Jpa is interface help you create some repositories and do not create crud method
 */
@Repository
public interface StorageRepository extends JpaRepository<ImageData,Long> {
   Optional<ImageData> findByName(String fileName);

}
