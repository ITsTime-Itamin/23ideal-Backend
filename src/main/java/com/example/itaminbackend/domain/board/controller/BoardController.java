package com.example.itaminbackend.domain.board.controller;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardController;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardResponseMessage;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/boards")
@Api(tags = "Board API")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시판 작성", notes = "게시판 글을 작성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponse>> createBoard(@Valid @ModelAttribute CreateRequest createRequest){
        CreateResponse createResponse = boardService.createBoard(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(EBoardController.eLocationIdPath.getValue())
                .buildAndExpand(createResponse.getBoardId())
                .toUri();
        return ResponseEntity.created(location).body(ResponseDto.create(EBoardResponseMessage.eCreateSuccess.getMessage(), createResponse));
    }
}
