package com.deciploy.backend.modules.api.activity.repository;

import com.deciploy.backend.modules.api.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String>, QuerydslPredicateExecutor<Activity>, CustomActivityRepository {
    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId")
    List<Activity> findActivityByUserId(@Param("userId") String userId);

    /*
    SELECT public.activity.name,
	EXTRACT(hour from "end_time") - EXTRACT(hour from "start_time") AS "hours",
	COALESCE(w.weightage,0) AS "weightage",
	(EXTRACT(hour from "end_time") - EXTRACT(hour from "start_time") ) * COALESCE(w.weightage,0) as score
    FROM  public.activity
    INNER JOIN public.application AS a ON public.activity.application_id = a.id
    INNER JOIN public.user_account AS u ON  public.activity.user_id = u.id
    LEFT JOIN public.weightage AS w ON a.type_id = w.application_type_id AND u.team_id = w.team_id
     */


}
