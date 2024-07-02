package com.example.foodrecognition.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ImageData is table in db: foodrecognition
 */
@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    private String name;

    private String path;

}
