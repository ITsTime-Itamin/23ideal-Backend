package com.example.itaminbackend.domain.scrap.controller;

import com.example.itaminbackend.domain.scrap.constant.ScrapConstants.EScrapResponseMessage;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapCountResponse;
import com.example.itaminbackend.domain.scrap.service.ScrapService;
import com.example.itaminbackend.global.dto.PaginationDto;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @ApiOperation(value = "게시판 스크랩 생성/삭제", notes = "게시판 스크랩을 생성/삭제 합니다.")
//    @PostMapping
//    public ResponseEntity<ResponseDto<ScrapChangeResponse>> changeScrap(@Valid @RequestBody ScrapChangeRequest scrapChangeRequest) {
//        ScrapChangeResponse scrapChangeResponse = this.scrapService.changeScrap(scrapChangeRequest, (Long)request.getAttribute(ScrapConstants.EHttpServletRequestAttribute.eUserId.getAttribute()));
//        if(scrapChangeResponse.isCreated()){
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                    .path("/{scrapId}")
//                    .buildAndExpand(scrapChangeResponse.getScrapId())
//                    .toUri();
//            return ResponseEntity.created(location).body(ResponseDto.create(EScrapResponseMessage.eCreateScrapMessage.getMessage(), scrapChangeResponse));
//        }
//        else return ResponseEntity.noContent().build();
//    }

//    @ApiOperation(value = "해당 유저의 해당 게시판 스크랩 여부 조회", notes = "해당 유저의 해당 게시판 스크랩 여부를 조회합니다.")
//    @PostMapping("/whether")
//    public ResponseEntity<ResponseDto<ScrapOrNotResponse>> getScrapOrNot(@Valid @RequestBody ScrapOrNotRequest scrapOrNotRequest) {
//        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.eScrapOrNotMessage.getMessage()
//                , this.scrapService.getScrapOrNot(scrapOrNotRequest, (Long)request.getAttribute(ScrapConstants.EHttpServletRequestAttribute.eUserId.getAttribute()))));
//    }

    @ApiOperation(value = "모든 게시판 스크랩 많은 순 조회", notes = "모든 게시판을 스크랩 많은 순으로 조회합니다.")
    @GetMapping("/ranking")
    public ResponseEntity<ResponseDto<PaginationDto<List<BoardInquiryByScrapRankingResponse>>>> getBoardsByScrapRanking(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.create(EScrapResponseMessage.GET_ALL_BOARDS_BY_SCRAP_RANKING.getMessage()
                , this.scrapService.getBoardsByScrapRanking(pageable)));
    }

}
