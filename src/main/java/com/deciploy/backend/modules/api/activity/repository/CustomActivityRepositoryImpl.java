package com.deciploy.backend.modules.api.activity.repository;

import com.deciploy.backend.modules.api.activity.dto.EmployeeScore;
import com.deciploy.backend.modules.api.activity.dto.TeamScore;
import com.deciploy.backend.modules.api.activity.entity.QActivity;
import com.deciploy.backend.modules.api.application.entity.QApplication;
import com.deciploy.backend.modules.api.weigtage.entity.QApplicationTypeWeightage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CustomActivityRepositoryImpl implements CustomActivityRepository {
    private final JPAQueryFactory queryFactory;

    private final QActivity qActivity;
    private final QApplication qApplication;
    private final QApplicationTypeWeightage qApplicationTypeWeightage;

    @Autowired
    public CustomActivityRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);

        qActivity = QActivity.activity;
        qApplication = QApplication.application;
        qApplicationTypeWeightage = QApplicationTypeWeightage.applicationTypeWeightage;
    }

    @Override
    public List<EmployeeScore> getEmployeeScores() {
        return getEmployeeScoreQuery()
                .fetch();
    }

    @Override
    public List<EmployeeScore> getEmployeeScores(Date date, boolean isFrom) {
        JPAQuery<EmployeeScore> query = getEmployeeScoreQuery();

        if (isFrom) {
            query.where(qActivity.startTime.goe(date));
        } else {
            query.where(qActivity.endTime.loe(date));
        }

        return query.fetch();
    }

    @Override
    public List<EmployeeScore> getEmployeeScores(Date from, Date to) {
        return getEmployeeScoreQuery()
                .where(qActivity.startTime.goe(from).and(qActivity.endTime.loe(to)))
                .fetch();
    }

    @Override
    public List<TeamScore> getTeamScores() {
        return getScoreQuery(TeamScore.class)
                .groupBy(QActivity.activity.user.team)
                .fetch();
    }

    private <T> JPAQuery<T> getScoreQuery(Class<T> type) {
        NumberExpression<Integer> score = qActivity.endTime.hour().subtract(qActivity.startTime.hour())
                .multiply(qApplicationTypeWeightage.weightage.coalesce(0)).sum().as("score");

        BooleanExpression weightageJoinCondition = qApplicationTypeWeightage.applicationType.id.eq(qApplication.type.id)
                .and(qApplicationTypeWeightage.team.id.eq(qActivity.user.team.id));

        return queryFactory
                .select(Projections.bean(type, qActivity.user, score))
                .from(qActivity)
                .leftJoin(qApplicationTypeWeightage)
                .on(weightageJoinCondition);
    }

    private JPAQuery<EmployeeScore> getEmployeeScoreQuery() {
        JPAQuery<EmployeeScore> query = getScoreQuery(EmployeeScore.class);

        return query
                .groupBy(QActivity.activity.user);
    }
}
