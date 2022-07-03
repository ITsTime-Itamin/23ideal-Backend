package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.BaseTest;
import com.example.itaminbackend.common.factory.BoardFactory;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.dto.BoardMapperSupport;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.exception.NotFoundBoardException;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.image.service.ImageService;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class BoardServiceImplTest extends BaseTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private ImageService imageService;

    @Spy
    @InjectMocks
    private BoardMapper boardMapper = Mappers.getMapper(BoardMapper.class);

    @Mock
    private BoardMapperSupport boardMapperSupport;

    private static MockedStatic<SecurityUtils> securityUtilsMock;
    private static User user;

    @BeforeAll
    static void beforeAll() {
        user = new User();
        user.setUserId(1000L);
        securityUtilsMock = mockStatic(SecurityUtils.class);
        securityUtilsMock.when(SecurityUtils::getLoggedInUser).thenReturn(user);
    }

    @AfterAll
    static void afterAll() {
        securityUtilsMock.close();
    }

    @DisplayName("게시판 작성 테스트")
    @Test
    void createBoardTest() {
        //given
        CreateRequest createRequest = BoardFactory.mockCreateRequests().get(0);
        Board board = Board.builder()
                .boardId(1L)
                .title("testTitle")
                .content("testContent")
                .images(extractImageFrom(createRequest))
                .build();
        given(this.boardRepository.save(any(Board.class))).willReturn(board);

        // when
        CreateResponse createResponse = this.boardService.createBoard(createRequest);

        // then
        assertThat(createResponse.getBoardId()).isNotNull();
        then(this.boardRepository).should().save(any(Board.class));
    }

    @DisplayName("게시판 수정 테스트")
    @Test
    void updateBoardTest() {
        //given
        UpdateRequest updateRequest = BoardFactory.mockUpdateRequests().get(0);
        Board board = Board.builder()
                .boardId(101L)
                .title("testTitle")
                .content("testContent")
                .images(extractImageFrom(updateRequest))
                .user(user)
                .build();
        given(this.boardRepository.findNotDeletedByBoardId(anyLong())).willReturn(Optional.of(board));
        given(this.imageService.saveImages(updateRequest.getFiles())).willReturn(extractImageFrom(updateRequest));

        UpdateResponse response = UpdateResponse.builder()
                .boardId(101L)
                .build();
        // when
        UpdateResponse updateResponse = this.boardService.updateBoard(updateRequest);

        // then
        assertThat(updateResponse)
                .usingRecursiveComparison()
                .isEqualTo(response);
        then(this.boardRepository).should().findNotDeletedByBoardId(anyLong());
        then(this.imageService).should(times(2)).deleteImage(any());
        then(this.imageService).should().saveImages(updateRequest.getFiles());
    }

    @DisplayName("존재하지 않는 boardId 요청시 예외를 발생한다.")
    @Test
    void validateBoardIdTest(){
        //given
        given(this.boardRepository.findNotDeletedByBoardId(any())).willReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> this.boardService.validateBoardId(anyLong()))
                .isInstanceOf(NotFoundBoardException.class);
    }

    private List<Image> extractImageFrom(CreateRequest createRequest) {
        List<Image> myFiles = extractImageNameFrom(createRequest).stream()
                .map(Image::new)
                .collect(toList());
        return myFiles;
    }

    private List<String> extractImageNameFrom(CreateRequest requestDto) {
        return requestDto.getFiles().stream()
                .map(MultipartFile::getName)
                .collect(toList());
    }

    private List<Image> extractImageFrom(UpdateRequest updateRequest) {
        List<Image> myFiles = extractImageNameFrom(updateRequest).stream()
                .map(Image::new)
                .collect(toList());
        return myFiles;
    }

    private List<String> extractImageNameFrom(UpdateRequest updateRequest) {
        return updateRequest.getFiles().stream()
                .map(MultipartFile::getName)
                .collect(toList());
    }



}