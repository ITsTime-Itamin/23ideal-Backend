package com.example.itaminbackend.domain.comment.dto;

import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommentMapperSupport.class, imports = SecurityUtils.class)
public interface CommentMapper {

    @Mapping(target = "content", source = "createRequest.content")
    @Mapping(target = "board", source = "createRequest.boardId", qualifiedByName = "getBoard")
    @Mapping(target = "user", expression = "java(SecurityUtils.getLoggedInUser())")
    Comment toEntity(CreateRequest createRequest);

    @Mapping(target = "commentId", source = "commentId")
    CreateResponse toCreateResponse(Comment comment);

}
