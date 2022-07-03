package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

@Mapper(componentModel = "spring", uses = BoardMapperSupport.class, imports = SecurityUtils.class)
public interface BoardMapper{

    @Mapping(target = "title", source = "createRequest.title")
    @Mapping(target = "content", source = "createRequest.content")
    @Mapping(target = "boardType", source = "createRequest.boardType")
    @Mapping(target = "deadLineDate", source = "createRequest.deadLineDate", qualifiedByName = "toLocalDateTime")
    @Mapping(target = "images", source = "createRequest.files", qualifiedByName = "saveImages")
    @Mapping(target = "user", expression = "java(SecurityUtils.getLoggedInUser())")
    Board toEntity(CreateRequest createRequest);

    @Mapping(target = "boardId", source = "boardId")
    CreateResponse toCreateResponse(Board board);

    @Mapping(target = "boardId", source = "boardId")
    UpdateResponse toUpdateResponse(Board board);

    @Mapping(target = "boardId", source = "boardId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "boardType", source = "boardType")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "imageKeys", source = "images", qualifiedByName = "getImageKeys")
    GetDetailResponse toGetDetailResponse(Board board);

}
