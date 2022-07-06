package com.example.itaminbackend.domain.comment.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.itaminbackend.domain.board.entity.QBoard.board;
import static com.example.itaminbackend.domain.comment.entity.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Comment> findNotDeletedByCommentId(Long commentId) {
        return Optional.ofNullable(queryFactory.selectFrom(comment)
                .where(boardIdEq(commentId),
                        isDeletedCheck())
                .fetchFirst());
    }

    @Override
    public List<Comment> findAllCommentsByBoardId(Long boardId) {
        return queryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .fetchJoin()
                .where(comment.board.boardId.eq(boardId))
                .orderBy(
                        comment.parent.commentId.asc().nullsFirst(),
                        comment.createdDate.asc()
                ).fetch();
    }

    private BooleanExpression isDeletedCheck() {
        return comment.isDeleted.eq(false);
    }

    private BooleanExpression boardIdEq(Long commentId) {
        return commentId != null ? comment.commentId.eq(commentId) : null;
    }

}
