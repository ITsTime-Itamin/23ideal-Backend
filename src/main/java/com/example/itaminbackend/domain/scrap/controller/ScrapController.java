package com.example.itaminbackend.domain.scrap.controller;

import com.example.itaminbackend.domain.scrap.constant.ScrapConstants;
import com.example.itaminbackend.domain.scrap.constant.ScrapConstants.EScrapResponseMessage;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.*;
import com.example.itaminbackend.domain.scrap.service.ScrapService;
import com.example.itaminbackend.global.dto.PaginationDto;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/scraps")
@RequiredArgsConstructor
@Api(tags = "Scrap API")
public class ScrapController {

    private final ScrapService scrapService;

    @ApiOperation(value = "게시판의 스크랩 수 조회", notes = "게시판의 스크랩 수를 조회합니다.")
    @GetMapping("{boardId}")
    public ResponseEntity<ResponseDto<ScrapCountResponse>> getScrapCount(@PathVariable Long boardId) {
        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.SCRAP_COUNT_SUCCESS.getMessage()
                , this.scrapService.getScrapCount(boardId)));
    }

    @ApiOperation(value = "게시판 스크랩 생성/삭제", notes = "게시판 스크랩을 생성/삭제 합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<ScrapChangeResponse>> changeScrap(@Valid @RequestBody ScrapChangeRequest scrapChangeRequest) {
        ScrapChangeResponse scrapChangeResponse = this.scrapService.changeScrap(scrapChangeRequest);
        if(scrapChangeResponse.getIsCreated()){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{scrapId}")
                    .buildAndExpand(scrapChangeResponse.getScrapId())
                    .toUri();
            return ResponseEntity.created(location).body(ResponseDto.create(EScrapResponseMessage.CREATE_SCRAP_SUCCESS.getMessage(), scrapChangeResponse));
        }
        else return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "해당 유저의 해당 게시판 스크랩 여부 조회", notes = "해당 유저의 해당 게시판 스크랩 여부를 조회합니다.")
    @PostMapping("/whether")
    public ResponseEntity<ResponseDto<ScrapOrNotResponse>> getScrapOrNot(@Valid @RequestBody ScrapOrNotRequest scrapOrNotRequest) {
        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.SCRAP_WHETHER_SUCCESS.getMessage()
                , this.scrapService.getScrapOrNot(scrapOrNotRequest)));
    }

    @ApiOperation(value = "모든 게시판 스크랩 많은 순 조회", notes = "모든 게시판을 스크랩 많은 순으로 조회합니다.")
    @GetMapping("/ranking")
    public ResponseEntity<ResponseDto<PaginationDto<List<BoardInquiryByScrapRankingResponse>>>> getBoardsByScrapRanking(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.GET_ALL_BOARDS_BY_SCRAP_RANKING.getMessage()
                , this.scrapService.getBoardsByScrapRanking(pageable)));
    }

    @ApiOperation(value = "해당 유저의 스크랩한 게시판 목록 조회", notes = "해당 유저의 스크랩한 게시판 목록 조회")
    @GetMapping("/whether/users")
    public ResponseEntity<ResponseDto<PaginationDto<List<ScrapedBoardsByUserResponse>>>> getScrapedBoardsByUser(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.GET_SCRAPED_BOARDS_BY_USER.getMessage()
                , this.scrapService.getScrapedBoardsByUser(pageable)));
    }
}