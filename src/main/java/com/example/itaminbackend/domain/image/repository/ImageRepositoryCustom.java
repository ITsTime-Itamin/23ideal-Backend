package com.example.itaminbackend.domain.image.repository;

import com.example.itaminbackend.domain.image.entity.Image;

import java.util.Optional;

public interface ImageRepositoryCustom {
    Optional<Image> findNotDeletedByImageId(Long imageId);
}
