package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.constant.BoardConnectionConstants.EDiaryConnectionServiceImpl;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.dto.BoardMapperSupport;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.entity.BoardConnection;
import com.example.itaminbackend.domain.board.exception.NotBoardWriterException;
import com.example.itaminbackend.domain.board.exception.NotFoundBoardException;
import com.example.itaminbackend.domain.board.repository.BoardConnectionRepository;
import com.example.itaminbackend.domain.board.repository.BoardRepository;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.image.service.ImageService;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.config.security.util.SecurityUtils;
import com.example.itaminbackend.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.itaminbackend.domain.board.dto.BoardDto.GetAllResponse;
import static com.example.itaminbackend.domain.board.dto.BoardDto.GetDetailResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final ImageService imageService;
    private final BoardMapperSupport boardMapperSupport;
    private final BoardConnectionRepository boardConnectionRepository;

    /**
     * Command
     */

    @Override
    public CreateResponse createBoard(CreateRequest createRequest) {
        Board board = this.boardMapper.toEntity(createRequest);
        return this.boardMapper.toCreateResponse(this.boardRepository.save(board));
    }

    @Override
    public UpdateResponse updateBoard(UpdateRequest updateRequest) {
        Board board = this.validateBoardId(Long.parseLong(updateRequest.getBoardId()));
        this.validateCreatedUser(board);
        board.setContent(updateRequest.getContent());
        board.setTitle(updateRequest.getTitle());
        board.setBoardType(EBoardType.valueOf(updateRequest.getBoardType()));
        board.setDeadLineDate(this.boardMapperSupport.toLocalDateTime(updateRequest.getDeadLineDate()));
        for (Image image : board.getImages()) {
            this.imageService.deleteImage(image.getImageId());
        }
        List<Image> images = this.imageService.saveImages(updateRequest.getFiles());
        board.setImages(images);
        return this.boardMapper.toUpdateResponse(board);
    }

    @Override
    public Board deleteBoard(Long boardId) {
        Board board = this.validateBoardId(boardId);
        board.setDeleted(true);
        return board;
    }

    @Override
    public GetDetailResponse getDetailBoard(Long boardId) {
        Board board = this.validateBoardId(boardId);
        Optional<List<BoardConnection>> findBoardConnection = this.boardConnectionRepository.findByBoard_BoardId(boardId);
        User user = SecurityUtils.getLoggedInUser();
        if(findBoardConnection.isEmpty()) this.boardConnectionRepository.save(BoardConnection.toEntity(board, user));
        else {
            BoardConnection boardConnection = validateCurrentModifiedDateDiaryConnection(findBoardConnection.get());
            if (boardConnection != null) boardConnection.setLastModifiedDate(LocalDateTime.now());
            else this.boardConnectionRepository.save(BoardConnection.toEntity(board, user));
        }
        Integer diaryConnectionCount = this.boardConnectionRepository.findConnectionCountByBoard(board).get();
        return this.boardMapper.toGetDetailResponse(board, diaryConnectionCount);
    }

    /**
     * Query
     */

    @Override
    @Transactional(readOnly=true)
    public PaginationDto<List<GetAllResponse>> getAllDetailBoards(Pageable pageable, String boardType) {
        Page<GetAllResponse> page = this.boardRepository.findAllDetailBoardsByCreatedDate(pageable, boardType);
        List<GetAllResponse> data = page.get().collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }

    /**
     * validate
     */

    public Board validateBoardId(Long boardId) {
        return this.boardRepository.findNotDeletedByBoardId(boardId).orElseThrow(NotFoundBoardException::new);
    }

    public void validateCreatedUser(Board board) {
        if(!(board.getUser().getUserId().equals(SecurityUtils.getLoggedInUser().getUserId()))) throw new NotBoardWriterException();
    }

    private BoardConnection validateCurrentModifiedDateDiaryConnection(List<BoardConnection> boardConnectionList) {
        LocalDateTime localDateTime = LocalDateTime.now();
        for (BoardConnection diaryConnection : boardConnectionList) {
            LocalDateTime lastModifiedDate = diaryConnection.getLastModifiedDate();
            if (lastModifiedDate.until(localDateTime, ChronoUnit.MINUTES) < EDiaryConnectionServiceImpl.eThirtyMinutes.getValue()) {
                return diaryConnection;
            }
        } return null;
    }

}
