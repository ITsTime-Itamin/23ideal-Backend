package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.entity.BoardConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardConnectionRepository extends JpaRepository<BoardConnection, Long> {

    Optional<List<BoardConnection>> findByBoard_BoardId(Long boardId);

    @Query("select count(*) from BoardConnection bc where bc.board = :board")
    Optional<Integer> findConnectionCountByBoard(Board board);
}
