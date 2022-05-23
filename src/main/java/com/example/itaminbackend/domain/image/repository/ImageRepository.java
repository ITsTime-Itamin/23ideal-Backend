package com.example.itaminbackend.domain.image.repository;

import com.example.itaminbackend.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {

}
