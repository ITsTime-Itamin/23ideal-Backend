package com.example.itaminbackend.domain.board.controller;

import com.example.itaminbackend.BaseWebMvcTest;
import com.example.itaminbackend.common.factory.BoardFactory;
import com.example.itaminbackend.common.factory.FileFactory;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardResponseMessage.CREATE_BOARD_SUCCESS;
import static com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardResponseMessage.UPDATE_BOARD_SUCCESS;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardControllerTest extends BaseWebMvcTest {

    @DisplayName("게시판 작성 테스트 - 성공")
    @Test
    @WithMockUser
    void createBoardTest_success() throws Exception {
        //given
        CreateResponse createResponse = BoardFactory.mockCreateResponses().get(0);
        given(boardService.createBoard(any(CreateRequest.class)))
                .willReturn(createResponse);
        //when
        ResultActions perform = mockMvc.perform(multipart("/api/v1/boards")
                .file(FileFactory.getTestImage1())
                .param("title", "boardTitle")
                .param("content", "boardContent")
                .param("boardType", "NOTICE")
                .param("deadLineDate", "2016-10-15 00:00:00"));

        //then
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(containsString(CREATE_BOARD_SUCCESS.getMessage())))
                .andExpect(jsonPath("$.data.boardId").value(is(1)))
                .andDo(print());
        then(this.boardService).should().createBoard(any(CreateRequest.class));
    }

    @DisplayName("게시판 수정 테스트 - 성공")
    @Test
    @WithMockUser
    void updateBoardTest_success() throws Exception {
        //given
        UpdateResponse updateResponse = BoardFactory.mockUpdateResponses().get(0);
        given(this.boardService.updateBoard(any(UpdateRequest.class)))
                .willReturn(updateResponse);

        //when
        ResultActions perform = mockMvc.perform(multipart("/api/v1/boards/update")
                .file(FileFactory.getTestImage1())
                .param("boardId", "1")
                .param("title", "boardTitle")
                .param("content", "boardContent")
                .param("boardType", "NOTICE")
                .param("deadLineDate", "2016-10-15 00:00:00"));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(containsString(UPDATE_BOARD_SUCCESS.getMessage())))
                .andExpect(jsonPath("$.data.boardId").value(is(1)))
                .andDo(print());
        then(this.boardService).should().updateBoard(any(UpdateRequest.class));
    }

}
