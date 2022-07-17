package com.example.itaminbackend.domain.scrap.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapedBoardsByUserResponse;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ScrapRepositoryCustom {
    Optional<Long> findScrapCountNotDeletedByBoard(Board board);
    Optional<Scrap> findByUserAndBoardAndNotDeleted(User user, Board board);
    Page<ScrapedBoardsByUserResponse> findBoardByUserAndScraped(User user, Pageable pageable);
    Page<BoardInquiryByScrapRankingResponse> findAllBoardsByScrapRanking(Pageable pageable);

}

