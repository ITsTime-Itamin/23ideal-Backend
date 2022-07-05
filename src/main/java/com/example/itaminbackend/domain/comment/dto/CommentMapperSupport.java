package com.example.itaminbackend.domain.comment.dto;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentMapperSupport {

    private final BoardService boardService;

    @Named("getBoard")
    public Board getBoard(Long boardId){
        return this.boardService.validateBoardId(boardId);
    }

}
