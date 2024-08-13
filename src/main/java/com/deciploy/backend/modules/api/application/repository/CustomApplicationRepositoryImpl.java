package com.deciploy.backend.modules.api.application.repository;

import com.deciploy.backend.modules.api.activity.entity.QActivity;
import com.deciploy.backend.modules.api.application.dto.ApplicationUsage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CustomApplicationRepositoryImpl implements CustomApplicationRepository {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CustomApplicationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ApplicationUsage> getApplicationUsage() {
        return getQuery().fetch();
    }

    @Override
    public List<ApplicationUsage> getApplicationUsage(Date date, boolean isFrom) {
        JPAQuery<ApplicationUsage> query = getQuery();

        if (isFrom) {
            query.where(QActivity.activity.startTime.goe(date));
        } else {
            query.where(QActivity.activity.endTime.loe(date));
        }

        return query.fetch();
    }

    @Override
    public List<ApplicationUsage> getApplicationUsage(Date from, Date to) {
        return getQuery()
                .where(QActivity.activity.startTime.goe(from).and(QActivity.activity.endTime.loe(to)))
                .fetch();
    }

    private JPAQuery<ApplicationUsage> getQuery() {
        QActivity qActivity = QActivity.activity;

        NumberExpression<Double> usage = Expressions
                .numberTemplate(Double.class, "extract(epoch from {0}) - extract(epoch from {1})", qActivity.endTime, qActivity.startTime)
                .divide(3600)
                .sum();

        return queryFactory
                .select(Projections.bean(ApplicationUsage.class, qActivity.application, usage.as("usage")))
                .from(qActivity)
                .groupBy(qActivity.application)
                .orderBy(usage.desc());
    }
}
