package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.BaseTest;
import com.example.itaminbackend.common.factory.BoardFactory;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.dto.BoardMapperSupport;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import com.example.itaminbackend.domain.myfile.entity.MyFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import static com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class BoardServiceImplTest extends BaseTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardServiceImpl boardServiceImpl;

    @Spy
    @InjectMocks
    private BoardMapper boardMapper = Mappers.getMapper(BoardMapper.class);

    @Mock
    private BoardMapperSupport boardMapperSupport;

    @DisplayName("게시판 작성 테스트")
    @Test
    void createBoardTest() {
        //given
        CreateRequest createRequest = BoardFactory.mockCreateRequests().get(0);
        Board board = Board.builder()
                .boardId(1L)
                .content("testContent")
                .myFiles(extractMyFilesFrom(createRequest))
                .build();
        given(this.boardRepository.save(any(Board.class))).willReturn(board);

        // when
        CreateResponse createResponse = this.boardServiceImpl.createBoard(createRequest);

        // then
        assertThat(createResponse.getBoardId()).isNotNull();
        then(this.boardRepository).should().save(any(Board.class));
    }

    private List<MyFile> extractMyFilesFrom(CreateRequest createRequest) {
        List<MyFile> myFiles = extractMyFileUrlsFrom(createRequest).stream()
                .map(MyFile::new)
                .collect(toList());
        return myFiles;
    }

    private List<String> extractMyFileUrlsFrom(CreateRequest requestDto) {
        return requestDto.getFiles().stream()
                .map(MultipartFile::getName)
                .collect(toList());
    }





}