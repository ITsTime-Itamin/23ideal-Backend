package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.*;

public interface BoardService {

    CreateResponse createBoard(CreateRequest createRequest);
    UpdateResponse updateBoard(UpdateRequest updateRequest);

}
