package com.example.itaminbackend.domain.board.service;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final ImageService imageService;

    @Override
    public CreateResponse createBoard(CreateRequest createRequest) {
        Board board = this.boardMapper.toEntity(createRequest);
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
        return this.boardMapper.toUpdateResponse(board);
    }

    /**
     * validate
     */

    public Board validateBoardId(Long boardId) {
        return this.boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
    }

}
