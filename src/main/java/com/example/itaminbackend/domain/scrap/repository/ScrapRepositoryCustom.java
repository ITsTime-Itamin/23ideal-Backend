package com.example.itaminbackend.domain.scrap.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScrapRepositoryCustom {
    Optional<Long> findScrapCountNotDeletedByBoard(Board board);
//    Optional<Scrap> findByUserAndBoardAndNotDeleted(User user, Board board);
    Page<BoardInquiryByScrapRankingResponse> findAllBoardsByScrapRanking(Pageable pageable);

}

