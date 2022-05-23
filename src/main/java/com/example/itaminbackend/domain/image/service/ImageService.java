package com.example.itaminbackend.domain.image.service;

import com.example.itaminbackend.domain.image.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<Image> saveImages(List<MultipartFile> multipartFiles);
    Image saveImage(MultipartFile multipartFiles);
    Image getImage(Long imageId);
    boolean deleteImage(Long imageId);
}
