package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.entity.Board;

import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<Board> findNotDeletedByBoardId(Long boardId);
}
