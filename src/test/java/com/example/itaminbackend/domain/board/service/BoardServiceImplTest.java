package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.BaseTest;
import com.example.itaminbackend.common.factory.BoardFactory;
import com.example.itaminbackend.common.factory.UserFactory;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.dto.BoardMapperSupport;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.exception.NotBoardWriterException;
import com.example.itaminbackend.domain.board.exception.NotFoundBoardException;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.image.service.ImageService;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import com.example.itaminbackend.global.dto.PaginationDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.web.multipart.MultipartFile;

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

    @DisplayName("게시판 작성 테스트 - 성공")
    @Test
    void createBoardTest_success() {
        //given
        CreateRequest createRequest = BoardFactory.mockCreateRequests().get(0);
        Board board = BoardFactory.mockBoards().get(0);
        board.setImages(extractImageFrom(createRequest));
        given(this.boardRepository.save(any(Board.class))).willReturn(board);

        // when
        CreateResponse createResponse = this.boardService.createBoard(createRequest);

        // then
        assertThat(createResponse.getBoardId()).isNotNull();
        then(this.boardRepository).should().save(any(Board.class));
    }

    @DisplayName("게시판 수정 테스트 - 성공")
    @Test
    void updateBoardTest() {
        //given
        UpdateRequest updateRequest = BoardFactory.mockUpdateRequests().get(0);
        Board board = BoardFactory.mockBoards().get(0);
        board.setUser(user);
        board.setImages(extractImageFrom(updateRequest));
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

    @DisplayName("게시판 삭제 테스트 - 성공")
    @Test
    void deleteBoardTest_success() {
        //given
        Board board = Board.builder()
                .boardId(101L)
                .isDeleted(false)
                .build();
        given(this.boardRepository.findNotDeletedByBoardId(anyLong())).willReturn(Optional.of(board));

        //when
        Board deletedBoard = this.boardService.deleteBoard(board.getBoardId());

        //then
        assertThat(deletedBoard.isDeleted()).isEqualTo(true);
        then(this.boardRepository).should().findNotDeletedByBoardId(anyLong());
    }

    @DisplayName("게시판 조회 테스트 - 성공")
    @Test
    void getDetailBoardTest_success() {
        //given
        Board board = BoardFactory.mockBoards().get(0);
        board.setUser(user);
        given(this.boardRepository.findNotDeletedByBoardId(anyLong())).willReturn(Optional.of(board));

        //when
        GetDetailResponse detailBoardResponse = this.boardService.getDetailBoard(board.getBoardId());

        //then
        assertThat(detailBoardResponse)
                .usingRecursiveComparison()
                .isEqualTo(this.boardMapper.toGetDetailResponse(board));
        then(this.boardRepository).should().findNotDeletedByBoardId(anyLong());
    }

    @DisplayName("게시판 작성 시간순 조회 테스트 - 성공")
    @Test
    void getAllDetailBoardsTest_success() {
        //given
        Board board1 = BoardFactory.mockBoards().get(0);
        Board board2 = BoardFactory.mockBoards().get(1);
        GetAllResponse getAllResponse1 = new GetAllResponse(board1.getBoardId(), board1.getTitle(), board1.getContent(), board1.getCreatedDate(), board1.getBoardType().toString(), board1.getImages().get(0).getImageKey(), user.getName());
        GetAllResponse getAllResponse2 = new GetAllResponse(board2.getBoardId(), board2.getTitle(), board2.getContent(), board2.getCreatedDate(), board1.getBoardType().toString(), board2.getImages().get(0).getImageKey(), user.getName());
        Page<GetAllResponse> page = PageableExecutionUtils.getPage(List.of(getAllResponse1, getAllResponse2), PageRequest.of(0, 10), () -> 2);
        PaginationDto<List<GetAllResponse>> data = PaginationDto.of(page, page.get().collect(toList()));
        given(this.boardRepository.findAllDetailBoardsByCreatedDate(any(), any())).willReturn(page);

        //when
        PaginationDto<List<GetAllResponse>> autual = this.boardService.getAllDetailBoards(PageRequest.of(0, 10), board1.getBoardType().toString());

        //then
        assertThat(autual)
                .usingRecursiveComparison()
                .isEqualTo(data);
    }

    @DisplayName("존재하지 않는 boardId 요청시 예외를 발생한다.")
    @Test
    void notFoundBoardExceptionTest(){
        //given
        given(this.boardRepository.findNotDeletedByBoardId(any())).willReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> this.boardService.validateBoardId(anyLong()))
                .isInstanceOf(NotFoundBoardException.class);
    }

    @DisplayName("해당 게시판을 작성한 유저가 아닐 때 예외를 발생한다.")
    @Test
    void notBoardWriterExceptionTest(){
        //given
        Board board = Board.builder()
                .boardId(101L)
                .user(UserFactory.mockUsers().get(0))
                .build();
        //when, then
        assertThatThrownBy(() -> this.boardService.validateCreatedUser(board))
                .isInstanceOf(NotBoardWriterException.class);
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