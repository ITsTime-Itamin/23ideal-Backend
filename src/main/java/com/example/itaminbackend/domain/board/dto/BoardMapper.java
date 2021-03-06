package com.example.itaminbackend.domain.board.dto;

import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.comment.dto.CommentDto;
import com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

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

    @Mapping(target = "boardId", source = "board.boardId")
    @Mapping(target = "title", source = "board.title")
    @Mapping(target = "content", source = "board.content")
    @Mapping(target = "boardType", source = "board.boardType")
    @Mapping(target = "createdDate", source = "board.createdDate")
    @Mapping(target = "deadLineDate", source = "board.deadLineDate")
    @Mapping(target = "imageKeys", source = "board.images", qualifiedByName = "getImageKeys")
    @Mapping(target = "userName", source = "board.user.name")
    @Mapping(target = "diaryConnectionCount", source = "diaryConnectionCount")
    GetDetailResponse toGetDetailResponse(Board board, Integer diaryConnectionCount);

}
