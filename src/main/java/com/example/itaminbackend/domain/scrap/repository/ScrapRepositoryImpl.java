package com.example.itaminbackend.domain.scrap.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.scrap.dto.QScrapDto_BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.QScrapDto_ScrapedBoardsByUserResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.ScrapedBoardsByUserResponse;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.user.entity.User;
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

import static com.example.itaminbackend.domain.board.entity.QBoard.board;
import static com.example.itaminbackend.domain.scrap.entity.QScrap.scrap;
import static com.example.itaminbackend.domain.user.entity.QUser.user;

public class ScrapRepositoryImpl implements ScrapRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ScrapRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Long> findScrapCountNotDeletedByBoard(Board board) {
        return Optional.ofNullable(queryFactory.selectFrom(scrap)
                .from(scrap)
                .where(boardEq(board),
                        isDeletedCheckOfScrap())
                .fetchCount());
    }

    @Override
    public Optional<Scrap> findByUserAndBoardAndNotDeleted(User user, Board board) {
        return Optional.ofNullable(queryFactory.selectFrom(scrap)
                .from(scrap)
                .where(boardEq(board),
                        userEq(user),
                        isDeletedCheckOfScrap())
                .fetchOne());
    }

    @Override
    public Page<ScrapedBoardsByUserResponse> findBoardByUserAndScraped(User inputUser, Pageable pageable) {
        List<ScrapedBoardsByUserResponse> content = queryFactory
                .select(new QScrapDto_ScrapedBoardsByUserResponse(
                        board.boardId
                ))
                .from(board)
                .leftJoin(board.scraps, scrap)
                .leftJoin(board.user, user)
                .where(userEq(inputUser), isDeletedCheckOfBoard(), isDeletedCheckOfScrap())
                .groupBy(board.boardId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<ScrapedBoardsByUserResponse> countQuery = queryFactory
                .select(new QScrapDto_ScrapedBoardsByUserResponse(
                        board.boardId
                ))
                .from(board)
                .leftJoin(board.scraps, scrap)
                .leftJoin(board.user, user)
                .where(userEq(inputUser), isDeletedCheckOfBoard(), isDeletedCheckOfScrap())
                .groupBy(board.boardId);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }

    @Override
    public Page<BoardInquiryByScrapRankingResponse> findAllBoardsByScrapRanking(Pageable pageable) {
        List<BoardInquiryByScrapRankingResponse> content = queryFactory
                .select(new QScrapDto_BoardInquiryByScrapRankingResponse(
                        board.boardId,
                        board.title,
                        board.createdDate,
                        scrapCount.sum()
                ))
                .from(board)
                .where(isDeletedCheckOfBoard())
                .leftJoin(board.scraps, scrap)
                .orderBy(scrapCount.sum().desc())
                .groupBy(board.boardId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<BoardInquiryByScrapRankingResponse> countQuery = queryFactory
                .select(new QScrapDto_BoardInquiryByScrapRankingResponse(
                        board.boardId,
                        board.title,
                        board.createdDate,
                        scrapCount.sum()
                ))
                .from(board)
                .where(isDeletedCheckOfBoard())
                .leftJoin(board.scraps, scrap)
                .orderBy(scrapCount.sum().desc())
                .groupBy(board.boardId);
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }
    NumberExpression<Long> scrapCount = scrap.isDeleted.
            when(false).then(new Long(1)).
            otherwise(new Long(0));

    private BooleanExpression isDeletedCheckOfScrap() {return scrap.isDeleted.eq(false);}
    private BooleanExpression isDeletedCheckOfBoard() {return board.isDeleted.eq(false);}
    private BooleanExpression boardEq(Board board) {return board != null ? scrap.board.eq(board) : null;}
    private BooleanExpression userEq(User user) {return user != null ? scrap.user.eq(user) : null;}
}
