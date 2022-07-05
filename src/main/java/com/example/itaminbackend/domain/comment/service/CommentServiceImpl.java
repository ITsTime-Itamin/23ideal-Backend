package com.example.itaminbackend.domain.comment.service;

import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateRequest;
import com.example.itaminbackend.domain.comment.dto.CommentDto.CreateResponse;
import com.example.itaminbackend.domain.comment.dto.CommentMapper;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.domain.comment.exception.NotFoundCommentException;
import com.example.itaminbackend.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

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
        return this.commentMapper.toCreateResponse(
                this.commentRepository.save(comment));
    }

    /**
     * validate
     */

    @Override
    public Comment validateCommentId(Long commentId){
        return this.commentRepository.findNotDeletedByCommentId(commentId).orElseThrow(NotFoundCommentException::new);
    }
}
