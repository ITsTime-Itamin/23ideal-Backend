package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.*;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    CreateResponse createBoard(CreateRequest createRequest);
    UpdateResponse updateBoard(UpdateRequest updateRequest);
    GetDetailResponse getDetailBoard(Long boardId);
    Board deleteBoard(Long boardId);
    PaginationDto<List<GetAllResponse>> getAllDetailBoards(Pageable pageable);

    Board validateBoardId(Long boardId);
    void validateCreatedUser(Board board);

}
