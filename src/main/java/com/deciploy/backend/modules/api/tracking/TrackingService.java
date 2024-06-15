package com.deciploy.backend.modules.api.tracking;

import com.deciploy.backend.modules.api.activity.entity.Activity;
import com.deciploy.backend.modules.api.activity.entity.QActivity;
import com.deciploy.backend.modules.api.activity.repository.ActivityRepository;
import com.deciploy.backend.modules.api.tracking.dto.ActivityFilter;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
@Log4j2
public class TrackingService {
    private final DateFormat dateFormat;

    @Autowired
    private ActivityRepository activityRepository;

    public TrackingService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
    }

    public List<Activity> getTracking(ActivityFilter filter) {
        QActivity qActivity = QActivity.activity;
        BooleanBuilder builder = new BooleanBuilder();

        if (filter.team().isPresent()) {
            builder.and(qActivity.user.team.id.eq(filter.team().get()));
        }

        if (filter.user().isPresent()) {
            builder.and(qActivity.user.id.eq(filter.user().get()));
        }

        if (filter.application().isPresent()) {
            builder.and(qActivity.application.id.eq(filter.application().get()));
        }

        if (filter.type().isPresent()) {
            builder.and(qActivity.application.type.id.eq(filter.type().get()));
        }

        if (filter.from().isPresent()) {
            try {
                builder.and(qActivity.startTime.goe(dateFormat.parse(filter.from().get())));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
            }
        }

        if (filter.to().isPresent()) {
            try {
                builder.and(qActivity.endTime.loe(dateFormat.parse(filter.to().get())));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
            }
        }

        return (List<Activity>) activityRepository.findAll(builder);
    }
}
