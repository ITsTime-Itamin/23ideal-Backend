package com.example.itaminbackend.domain.scrap.repository;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.scrap.dto.QScrapDto_BoardInquiryByScrapRankingResponse;
import com.example.itaminbackend.domain.scrap.dto.ScrapDto.BoardInquiryByScrapRankingResponse;
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
                        isDeletedCheck())
                .fetchCount());
    }

//    @Override
//    public Optional<Scrap> findByUserAndDiaryAndNotDeleted(User user, Diary diary) {
//        return Optional.ofNullable(queryFactory.selectFrom(scrap)
//                .from(scrap)
//                .where(diaryEq(diary),
//                        userEq(user),
//                        isDeletedCheck())
//                .fetchOne());
//    }

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
                .leftJoin(board.scraps, scrap)
                .orderBy(scrapCount.sum().desc())
                .groupBy(board.boardId);
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }
    NumberExpression<Long> scrapCount = scrap.isDeleted.
            when(false).then(new Long(1)).
            otherwise(new Long(0));

    private BooleanExpression isDeletedCheck() {return scrap.isDeleted.eq(false);}

    private BooleanExpression boardEq(Board board) {return board != null ? scrap.board.eq(board) : null;}
    //private BooleanExpression userEq(User user) {return user != null ? scrap.user.eq(user) : null;}
}
