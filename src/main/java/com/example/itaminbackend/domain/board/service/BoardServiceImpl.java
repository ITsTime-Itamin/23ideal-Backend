package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.dto.BoardDto;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.CreateResponse;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateRequest;
import com.example.itaminbackend.domain.board.dto.BoardDto.UpdateResponse;
import com.example.itaminbackend.domain.board.dto.BoardMapper;
import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.exception.NotFoundBoardException;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.itaminbackend.domain.board.dto.BoardDto.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final ImageService imageService;

    @Override
    public CreateResponse createBoard(CreateRequest createRequest) {
        Board board = this.boardMapper.toEntity(createRequest);
        User user = SecurityUtils.getLoggedInUser();
        //user.setBoards(board);
        return this.boardMapper.toCreateResponse(this.boardRepository.save(board));
    }

    @Override
    @Transactional
    public UpdateResponse updateBoard(UpdateRequest updateRequest) {
        Board board = this.validateBoardId(Long.parseLong(updateRequest.getBoardId()));
        return updateBoard(board, updateRequest);
    }

    private UpdateResponse updateBoard(Board board, UpdateRequest updateRequest) {
        board.setContent(updateRequest.getContent());
        board.setTitle(updateRequest.getTitle());
        board.setBoardType(EBoardType.valueOf(updateRequest.getBoardType()));
        for (Image image : board.getImages()) {
            this.imageService.deleteImage(image.getImageId());
        }
        List<Image> images = this.imageService.saveImages(updateRequest.getFiles());
        board.setImages(images);
        User user = SecurityUtils.getLoggedInUser();
        user.setBoards(board);
        return this.boardMapper.toUpdateResponse(board);
    }

    @Override
    public GetDetailResponse getDetailBoard(Long boardId) {
        return this.boardMapper.toGetDetailResponse(this.validateBoardId(boardId));
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = this.validateBoardId(boardId);
        board.setDeleted(true);
    }

    @Override
    public PaginationDto<List<GetAllResponse>> getAllDetailBoards(Pageable pageable) {
        Page<GetAllResponse> page = this.boardRepository.findAllDetailBoardsByCreatedDate(pageable);
        List<GetAllResponse> data = page.get().collect(Collectors.toList());
        return PaginationDto.of(page, data);
    }

    /**
     * validate
     */

    public Board validateBoardId(Long boardId) {
        return this.boardRepository.findNotDeletedByBoardId(boardId).orElseThrow(NotFoundBoardException::new);
    }

}
