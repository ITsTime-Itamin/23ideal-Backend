package com.example.itaminbackend.domain.myfile.dto;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.myfile.entity.MyFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyFileMapper {

    @Mapping(target = "fileKey", source = "key")
    MyFile toEntity(String key);
}
