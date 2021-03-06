package com.example.itaminbackend.domain.board.controller;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardController;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardResponseMessage;
import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
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

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/boards")
@Api(tags = "Board API")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시판 작성", notes = "게시판 글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponse>> createBoard(@Valid @ModelAttribute CreateRequest createRequest){
        CreateResponse createResponse = this.boardService.createBoard(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(EBoardController.LOCATION_ID_PATH.getValue())
                .buildAndExpand(createResponse.getBoardId())
                .toUri();
        return ResponseEntity.created(location).body(ResponseDto.create(EBoardResponseMessage.CREATE_BOARD_SUCCESS.getMessage(), createResponse));
    }

    @ApiOperation(value = "게시판 수정", notes = "게시판 글을 수정합니다.")
    @PostMapping("/update")
    public ResponseEntity<ResponseDto<UpdateResponse>> updateBoard(@Valid @ModelAttribute UpdateRequest updateRequest){
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.UPDATE_BOARD_SUCCESS.getMessage(), this.boardService.updateBoard(updateRequest)));
    }

    @ApiOperation(value = "게시판 조회", notes = "게시판 글을 조회합니다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto<GetDetailResponse>> getDetailBoard(@PathVariable Long boardId){
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.GET_DETAIL_BOARD_SUCCESS.getMessage(), this.boardService.getDetailBoard(boardId)));
    }

    @ApiOperation(value = "게시판 삭제", notes = "게시판 글을 삭제합니다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId){
        this.boardService.deleteBoard(boardId);
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.DELETE_BOARD_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "게시판 작성 시간순 조회", notes = "게시판 글을 작성 시간순으로 조회합니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<PaginationDto<List<GetAllResponse>>>> getAllDetailBoards(@PageableDefault Pageable pageable, @RequestParam(name="boardType") String boardType){
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.GET_ALL_DETAIL_BOARDS_SUCCESS.getMessage(), this.boardService.getAllDetailBoards(pageable, boardType)));
    }


}