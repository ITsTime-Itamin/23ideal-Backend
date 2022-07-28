package com.example.itaminbackend.domain.board.controller;

import com.example.itaminbackend.BaseWebMvcTest;
import com.example.itaminbackend.common.factory.BoardFactory;
import com.example.itaminbackend.common.factory.FileFactory;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.dto.BoardDto.*;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardResponseMessage.*;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardControllerTest extends BaseWebMvcTest {

//    @DisplayName("게시판 작성 테스트 - 성공")
//    @Test
//    @WithMockUser
//    void createBoardTest_success() throws Exception {
//        //given
//        CreateResponse createResponse = BoardFactory.mockCreateResponses().get(0);
//        given(boardService.createBoard(any(CreateRequest.class)))
//                .willReturn(createResponse);
//        //when
//        ResultActions perform = mockMvc.perform(multipart("/api/v1/boards")
//                .file(FileFactory.getTestImage1())
//                .param("title", "boardTitle")
//                .param("content", "boardContent")
//                .param("boardType", "NOTICE")
//                .param("deadLineDate", "2016-10-15 00:00:00"));
//
//        //then
//        perform.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.message").value(containsString(CREATE_BOARD_SUCCESS.getMessage())))
//                .andExpect(jsonPath("$.data.boardId").value(is(1)))
//                .andDo(print());
//        then(this.boardService).should().createBoard(any(CreateRequest.class));
//    }
//
//    @DisplayName("게시판 수정 테스트 - 성공")
//    @Test
//    @WithMockUser
//    void updateBoardTest_success() throws Exception {
//        //given
//        UpdateResponse updateResponse = BoardFactory.mockUpdateResponses().get(0);
//        given(this.boardService.updateBoard(any(UpdateRequest.class)))
//                .willReturn(updateResponse);
//
//        //when
//        ResultActions perform = mockMvc.perform(multipart("/api/v1/boards/update")
//                .file(FileFactory.getTestImage1())
//                .param("boardId", "1")
//                .param("title", "boardTitle")
//                .param("content", "boardContent")
//                .param("boardType", "NOTICE")
//                .param("deadLineDate", "2016-10-15 00:00:00"));
//
//        //then
//        perform.andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value(containsString(UPDATE_BOARD_SUCCESS.getMessage())))
//                .andExpect(jsonPath("$.data.boardId").value(is(1)))
//                .andDo(print());
//        then(this.boardService).should().updateBoard(any(UpdateRequest.class));
//    }

    @DisplayName("게시판 조회 테스트 - 성공")
    @Test
    @WithMockUser
    void getDetailBoardTest_success() throws Exception {
        //given
        GetDetailResponse getDetailResponse = BoardFactory.mockDetailResponses().get(0);
        given(this.boardService.getDetailBoard(any(Long.class)))
                .willReturn(getDetailResponse);

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/boards/{boardId}", 1L));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(containsString(GET_DETAIL_BOARD_SUCCESS.getMessage())))
                .andExpect(jsonPath("$.data.boardId").value(is(1)))
                .andExpect(jsonPath("$.data.title").value(is("title1")))
                .andExpect(jsonPath("$.data.content").value(is("content1")))
                .andExpect(jsonPath("$.data.boardType").value(is(EBoardType.FREE.toString())))
                .andExpect(jsonPath("$.data.createdDate").value(is("2022-07-10T16:10:05")))
                .andExpect(jsonPath("$.data.deadLineDate").value(is("2022-07-10T16:10:05")))
                .andExpect(jsonPath("$.data.imageKeys").value(is(List.of("files", "files"))))
                .andExpect(jsonPath("$.data.userName").value(is("kimjungwoo")))
                .andDo(print());
        then(this.boardService).should().getDetailBoard(any(Long.class));
    }

//    @DisplayName("게시판 작성시간 순 조회 테스트 - 성공")
//    @Test
//    @WithMockUser
//    void getAllDetailBoardsTest_success() throws Exception {
//        PageRequest.of(0, 10);
//        //given
//        GetDetailResponse getDetailResponse1 = BoardFactory.mockDetailResponses().get(0);
//        GetDetailResponse getDetailResponse2 = BoardFactory.mockDetailResponses().get(1);
//        given(this.boardService.getDetailBoard(any(Long.class)))
//                .willReturn(getDetailResponse);
//        Page<GetAllResponse> page = PageableExecutionUtils.getPage(List.of(getDetailResponse1, getAllResponse2), PageRequest.of(0, 10), () -> 2);
//        PaginationDto<List<GetAllResponse>> data = PaginationDto.of(page, page.get().collect(toList()));
//        given(this.boardService.getAllDetailBoards(PageRequest.of(0, 10)))
//                .willReturn(List.of(getDetailResponse1, getDetailResponse2));
//        //when
//        ResultActions perform = mockMvc.perform(get("/api/v1/boards/{boardId}", 1L));
//
//        //then
//        perform.andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value(containsString(GET_DETAIL_BOARD_SUCCESS.getMessage())))
//                .andExpect(jsonPath("$.data.boardId").value(is(1)))
//                .andExpect(jsonPath("$.data.title").value(is("title1")))
//                .andExpect(jsonPath("$.data.content").value(is("content1")))
//                .andExpect(jsonPath("$.data.boardType").value(is(EBoardType.FREE.toString())))
//                .andExpect(jsonPath("$.data.createdDate").value(is("2022-07-10T16:10:05")))
//                .andExpect(jsonPath("$.data.deadLineDate").value(is("2022-07-10T16:10:05")))
//                .andExpect(jsonPath("$.data.imageKeys").value(is(List.of("files", "files"))))
//                .andExpect(jsonPath("$.data.userName").value(is("kimjungwoo")))
//                .andDo(print());
//        then(this.boardService).should().getDetailBoard(any(Long.class));
//    }


    @DisplayName("게시판 삭제 테스트 - 성공")
    @Test
    @WithMockUser
    void deleteBoardTest_success() throws Exception {

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/boards/{boardId}", 1L));

        //then
        perform.andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").value(containsString(DELETE_BOARD_SUCCESS.getMessage())))
                        .andDo(print());
        then(this.boardService).should().deleteBoard(any(Long.class));
    }

}
