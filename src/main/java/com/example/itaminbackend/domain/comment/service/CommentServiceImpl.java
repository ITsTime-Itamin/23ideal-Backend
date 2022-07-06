package com.example.itaminbackend.domain.comment.service;

import com.example.itaminbackend.domain.board.service.BoardService;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.dto.CommentDto.DeleteRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse;
import com.example.itaminbackend.domain.comment.dto.CommentMapper;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.domain.comment.exception.NotFoundCommentException;
import com.example.itaminbackend.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.itaminbackend.domain.comment.dto.CommentDto.GetResponse.convertCommentToDto;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final BoardService boardService;

    /**
     * command
     */

    @Override
    public CreateResponse createComment(CreateRequest createRequest) {
        Long parentCommentId = createRequest.getParentCommentId();
        Comment parent = null;
        Comment comment = this.commentMapper.toEntity(createRequest);
        if(parentCommentId!=null) {
            parent = this.validateCommentId(parentCommentId);
            comment.setParent(parent);}
        return this.commentMapper.toCreateResponse(this.commentRepository.save(comment));
    }

    @Override
    public Comment deleteComment(DeleteRequest deleteRequest) {
        Comment comment = this.validateCommentId(deleteRequest.getCommentId());
        comment.setDeleted(true);
        return comment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetResponse> getAllCommentsByBoardId(Long boardId) {
        return this.convertNestedStructure(this.commentRepository.findAllCommentsByBoardId(boardId));
    }

    /**
     * validate
     */

    @Override
    public Comment validateCommentId(Long commentId){
        return this.commentRepository.findNotDeletedByCommentId(commentId).orElseThrow(NotFoundCommentException::new);
    }

    /**
     * util
     */

    private List<GetResponse> convertNestedStructure(List<Comment> comments) {
        List<GetResponse> result = new ArrayList<>();
        Map<Long, GetResponse> map = new HashMap<>();
        comments.stream().forEach(c -> {
            GetResponse getResponse = convertCommentToDto(c);
            map.put(getResponse.getCommentId(), getResponse);
            if(c.getParent() != null) map.get(c.getParent().getCommentId()).getChildren().add(getResponse);
            else result.add(getResponse);
        });
        return result;
    }
}
