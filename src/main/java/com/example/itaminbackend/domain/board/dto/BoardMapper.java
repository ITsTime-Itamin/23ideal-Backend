package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

@Mapper(componentModel = "spring", uses = BoardMapperSupport.class)
public interface BoardMapper{

    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "boardType", source = "boardType")
    @Mapping(target = "images", source = "files", qualifiedByName = "saveImages")
    Board toEntity(CreateRequest createRequest);

    @Mapping(target = "boardId", source = "boardId")
    CreateResponse toCreateResponse(Board board);

    @Mapping(target = "boardId", source = "boardId")
    UpdateResponse toUpdateResponse(Board board);

    @Mapping(target = "boardId", source = "boardId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "boardType", source = "boardType")
    @Mapping(target = "imageKeys", source = "images", qualifiedByName = "getImageKeys")
    GetDetailResponse toGetDetailResponse(Board board);


}
