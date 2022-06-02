package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.itaminbackend.domain.board.entity.QBoard.board;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Board> findNotDeletedByBoardId(Long boardId) {
        return Optional.ofNullable(queryFactory.selectFrom(board)
                .where(boardIdEq(boardId),
                        isDeletedCheck())
                .fetchFirst());
    }

    private BooleanExpression isDeletedCheck() {
        return board.isDeleted.eq(false);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return boardId != null ? board.boardId.eq(boardId) : null;
    }
}
