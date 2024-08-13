package com.deciploy.backend.modules.api.time.repository;

import com.deciploy.backend.modules.api.time.dto.WorkTime;
import com.deciploy.backend.modules.api.time.entity.QTime;
import com.deciploy.backend.modules.api.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CustomTimeRepositoryImpl implements CustomTimeRepository {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CustomTimeRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private JPAQuery<WorkTime> getWorkTime(User employee) {
        QTime qTime = QTime.time;

        NumberExpression<Double> time = Expressions
                .numberTemplate(Double.class, "extract(epoch from {0}) - extract(epoch from {1})", qTime.clockOut, qTime.clockIn)
                .sum()
                .as("time");

        DateTemplate<Date> dateTemplate = Expressions.dateTemplate(Date.class, "date({0})", qTime.clockIn);

        return queryFactory
                .select(Projections.bean(WorkTime.class, dateTemplate.as("date"), time))
                .from(qTime)
                .where(qTime.clockOut.isNotNull())
                .where(qTime.user.eq(employee))
                .groupBy(dateTemplate);
    }

    @Override
    public List<WorkTime> getWorkTimeByEmployee(User employee) {
        JPAQuery<WorkTime> query = getWorkTime(employee);

        return query.fetch();
    }

    @Override
    public List<WorkTime> getWorkTimeByEmployee(User employee, Date from, boolean isFrom) {
        JPAQuery<WorkTime> query = getWorkTime(employee);

        if (isFrom) {
            query.where(QTime.time.clockIn.goe(from));
        } else {
            query.where(QTime.time.clockIn.loe(from));
        }

        return query.fetch();
    }

    @Override
    public List<WorkTime> getWorkTimeByEmployee(User employee, Date from, Date to) {
        JPAQuery<WorkTime> query = getWorkTime(employee);

        return query
                .where(QTime.time.clockIn.goe(from))
                .where(QTime.time.clockIn.loe(to))
                .fetch();
    }
}
