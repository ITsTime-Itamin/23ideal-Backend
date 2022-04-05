package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LongSummaryStatistics;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
