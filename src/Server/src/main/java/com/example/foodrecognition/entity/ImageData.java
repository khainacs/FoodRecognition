package com.example.foodrecognition.entity;

import lombok.*;

import javax.persistence.*;

/**
 * ImageData is table in db: foodrecognition
 */

@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ImageData {
    @Id
    private String name;

    private String type;

    private String path;

    @Lob
    @Column(name = "imageData", length = 1000)
    private byte[] imageData;


}
