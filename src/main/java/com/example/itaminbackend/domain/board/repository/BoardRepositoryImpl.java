package com.example.itaminbackend.domain.board.repository;

import com.example.itaminbackend.domain.board.constant.BoardConstants;
import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.board.dto.QBoardDto_GetAllResponse;
import com.example.itaminbackend.domain.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.itaminbackend.domain.board.dto.BoardDto.GetAllResponse;
import static com.example.itaminbackend.domain.board.entity.QBoard.board;
import static com.example.itaminbackend.domain.image.entity.QImage.image;
import static com.example.itaminbackend.domain.scrap.entity.QScrap.scrap;
import static com.example.itaminbackend.domain.user.entity.QUser.user;

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

    @Override
    public Page<GetAllResponse> findAllDetailBoardsByCreatedDate(Pageable pageable, String boardType) {
        List<GetAllResponse> content = queryFactory
                .select(new QBoardDto_GetAllResponse(board.boardId,
                        board.title,
                        board.content,
                        board.createdDate,
                        board.boardType.stringValue(),
                        image.imageKey,
                        user.name,
                        scrapCount.sum()))
                .from(board)
                .leftJoin(board.images, image)
                .leftJoin(board.user, user)
                .leftJoin(board.scraps, scrap)
                .where(isDeletedCheck(), boardTypeEq(boardType))
                .orderBy(board.createdDate.desc())
                .groupBy(board.boardId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<GetAllResponse> countQuery = queryFactory
                .select(new QBoardDto_GetAllResponse(board.boardId,
                        board.title,
                        board.content,
                        board.createdDate,
                        board.boardType.stringValue(),
                        image.imageKey,
                        user.name,
                        scrapCount.sum()))
                .from(board)
                .leftJoin(board.images, image)
                .leftJoin(board.user, user)
                .leftJoin(board.scraps, scrap)
                .where(isDeletedCheck())
                .orderBy(board.createdDate.desc())
                .groupBy(board.boardId);
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }

    NumberExpression<Long> scrapCount = scrap.isDeleted.
            when(false).then(new Long(1)).
            otherwise(new Long(0));

    private BooleanExpression isDeletedCheck() {
        return board.isDeleted.eq(false);
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return boardId != null ? board.boardId.eq(boardId) : null;
    }
    private BooleanExpression boardTypeEq(String boardType) {
        return boardType != null ? board.boardType.eq(EBoardType.valueOf(boardType)) : null;
    }
}
