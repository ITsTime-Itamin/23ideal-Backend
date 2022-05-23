package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public CreateResponse createBoard(CreateRequest createRequest) {
        Board board = this.boardMapper.toEntity(createRequest);
        return this.boardMapper.toCreateResponse(this.boardRepository.save(board));
    }

    @Override
    @Transactional
    public UpdateResponse updateBoard(UpdateRequest updateRequest) {
        Optional<Board> board = this.boardRepository.findById(Long.valueOf(updateRequest.getBoardId()));
        return updateBoard(board, updateRequest);
    }

    private UpdateResponse updateBoard(Optional<Board> board, UpdateRequest updateRequest) {
        Board findBoard = board.get();
        findBoard.setContent("setcontent");
        findBoard.setTitle("title");
        return UpdateResponse.from(this.boardRepository.save(findBoard));
    }
}
