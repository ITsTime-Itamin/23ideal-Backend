package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public CreateResponse createBoard(CreateRequest createRequest) {
        Board board = this.boardMapper.toEntity(createRequest);
        System.out.println("boardFileSize"+board.getMyFiles().size());
        return this.boardMapper.toCreateResponse(this.boardRepository.save(board));
    }
}
