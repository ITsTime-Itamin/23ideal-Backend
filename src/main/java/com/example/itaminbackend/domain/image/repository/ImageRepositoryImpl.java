package com.example.itaminbackend.domain.image.repository;

import com.example.itaminbackend.domain.image.entity.Image;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.itaminbackend.domain.image.entity.QImage.image;


public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ImageRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Image> findNotDeletedByImageId(Long imageId) {
        return Optional.ofNullable(queryFactory.selectFrom(image)
                .from(image)
                .where(imageIdEq(imageId),
                 isDeletedCheck())
                .fetchFirst());
    }

    private BooleanExpression isDeletedCheck() {
        return image.isDeleted.eq(false);
    }

    private BooleanExpression imageIdEq(Long imageId) {
        return imageId != null ? image.imageId.eq(imageId) : null;
    }
}
