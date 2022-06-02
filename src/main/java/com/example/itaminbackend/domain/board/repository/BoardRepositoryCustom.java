package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

public interface BoardRepositoryCustom {
    Optional<Board> findNotDeletedByBoardId(Long boardId);
    Page<GetAllResponse> findAllDetailBoardsByCreatedDate(Pageable pageable);

}
