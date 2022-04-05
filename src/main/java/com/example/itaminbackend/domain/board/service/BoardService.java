package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.dto.BoardDto.*;

public interface BoardService {

    CreateResponse createBoard(CreateRequest createRequest);
}
