package com.example.itaminbackend.domain.scrap.service;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.*;
import com.example.itaminbackend.domain.scrap.dto.ScrapMapper;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.scrap.repository.ScrapRepository;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import com.example.itaminbackend.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;
    private final BoardService boardService;
    private final ScrapMapper scrapMapper;

    @Override
    public ScrapCountResponse getScrapCount(Long boardId) {
        return this.scrapMapper.toScrapCountResponse(this.scrapRepository
                .findScrapCountNotDeletedByBoard(this.boardService.validateBoardId(boardId)).get());
    }

    @Override
    @Transactional
    public ScrapChangeResponse changeScrap(ScrapChangeRequest scrapChangeRequest) {
        User user = SecurityUtils.getLoggedInUser();
        Board board = this.boardService.validateBoardId(scrapChangeRequest.getBoardId());
        Optional<Scrap> scrap = this.scrapRepository.findByUserAndBoard(user, board);
        if(scrap.isEmpty()) {
            Scrap savedScrap = this.scrapRepository.save(scrapMapper.toEntity(user, board));
            return this.scrapMapper.toScrapChangeResponse(savedScrap.getScrapId(), true);}
        else if(scrap.get().isDeleted()) {
            scrap.get().setDeleted(false);
            return this.scrapMapper.toScrapChangeResponse(scrap.get().getScrapId(), true); }
        else if(!scrap.get().isDeleted())
            scrap.get().setDeleted(true);
            return this.scrapMapper.toScrapChangeResponse(scrap.get().getScrapId(), false);
    }

    @Override
    public ScrapOrNotResponse getScrapOrNot(ScrapOrNotRequest scrapOrNotRequest) {
        User user = SecurityUtils.getLoggedInUser();
        Board board = this.boardService.validateBoardId(scrapOrNotRequest.getBoardId());
        if(this.scrapRepository.findByUserAndBoardAndNotDeleted(user, board).isEmpty()) return this.scrapMapper.toScrapOrNotResponse(false);
        else return this.scrapMapper.toScrapOrNotResponse(true);
    }

    @Override
    public PaginationDto<List<BoardInquiryByScrapRankingResponse>> getBoardsByScrapRanking(Pageable pageable) {
        Page<BoardInquiryByScrapRankingResponse> page = this.scrapRepository.findAllBoardsByScrapRanking(pageable);
        List<BoardInquiryByScrapRankingResponse> data = page.get().collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }
}
