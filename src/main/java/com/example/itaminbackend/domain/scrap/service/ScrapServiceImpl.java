package com.example.itaminbackend.domain.scrap.service;

import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapCountResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapMapper;
import com.example.itaminbackend.domain.scrap.repository.ScrapRepository;
import com.example.itaminbackend.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;
    private final BoardService boardService;
    //private final UserService userService;
    private final ScrapMapper scrapMapper;

    @Override
    public ScrapCountResponse getScrapCount(Long boardId) {
        return this.scrapMapper.toScrapCountResponse(this.scrapRepository
                .findScrapCountNotDeletedByBoard(this.boardService.validateBoardId(boardId)).get());
    }

//    @Override
//    @Transactional
//    public ScrapChangeResponse changeScrap(ScrapChangeRequest scrapChangeRequest, Long userId) {
//        User user = this.userService.getUserByUserId(userId);
//        Board board = this.diaryService.getBoardByBoardId(scrapChangeRequest.getBoardId());
//        Optional<Scrap> scrap = this.scrapRepository.findByUserAndBoard(user, board);
//        if(scrap.isEmpty()) {
//            Scrap savedScrap = this.scrapRepository.save(Scrap.toEntity(user, board));
//            return ScrapChangeResponse.from(savedScrap.getScrapId(), true);}
//        else if(scrap.get().isDeleted()) {
//            scrap.get().setDeleted(false);
//            return ScrapChangeResponse.from(scrap.get().getScrapId(), true); }
//        else if(!scrap.get().isDeleted())
//            scrap.get().setDeleted(true);
//            return ScrapChangeResponse.from(scrap.get().getScrapId(), false);
//    }
//
//    @Override
//    public ScrapOrNotResponse getScrapOrNot(ScrapOrNotRequest scrapOrNotRequest, Long userId) {
//        User user = this.userService.getUserByUserId(userId);
//        Board board = this.boardService.getBoardByBoardId(scrapOrNotRequest.getBoardId());
//        if(this.scrapRepository.findByUserAndBoardAndNotDeleted(user, board).isEmpty()) return ScrapOrNotResponse.from(false);
//        else return ScrapOrNotResponse.from(true);
//    }

    @Override
    public PaginationDto<List<BoardInquiryByScrapRankingResponse>> getBoardsByScrapRanking(Pageable pageable) {
        Page<BoardInquiryByScrapRankingResponse> page = this.scrapRepository.findAllBoardsByScrapRanking(pageable);
        List<BoardInquiryByScrapRankingResponse> data = page.get().collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }
}
