package com.example.itaminbackend.domain.image.dto;

import com.example.itaminbackend.domain.image.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "imageKey", source = "key")
    Image toEntity(String key);
}
