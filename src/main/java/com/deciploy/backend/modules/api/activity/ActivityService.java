package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.dto.*;
import com.deciploy.backend.modules.api.activity.entity.Activity;
import com.deciploy.backend.modules.api.activity.repository.ActivityRepository;
import com.deciploy.backend.modules.api.application.ApplicationService;
import com.deciploy.backend.modules.api.application.entity.Application;
import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ActivityService {
    private final DateFormat dateFormat;
    @Autowired
    private AuthService authService;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ApplicationService applicationService;

    public ActivityService() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
    }

    public void sync(List<ActivitySyncRequest> activitySyncRequests) {
        try {
            for (ActivitySyncRequest activitySyncRequest : activitySyncRequests) {
                Optional<Application> application = applicationService
                        .getApplication(activitySyncRequest.name().toLowerCase());

                if (application.isEmpty()) {
                    continue;
                }

                Activity activity = Activity.builder()
                        .name(activitySyncRequest.name())
                        .title(activitySyncRequest.title())
                        .startTime(dateFormat.parse(activitySyncRequest.startTime()))
                        .endTime(dateFormat.parse(activitySyncRequest.endTime()))
                        .user(authService.getAuthenticatedUser())
                        .application(application.get())
                        .build();

                activityRepository.save(activity);
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    public List<Activity> findByUser(String userId) {
        return activityRepository.findActivityByUserId(userId);
    }

    public List<EmployeeScore> getEmployeeScore(ScoreFilter filter) {
        try {
            if (filter.from().isPresent() && filter.to().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getEmployeeScores(fromDate, toDate);
            } else if (filter.from().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                return activityRepository.getEmployeeScores(fromDate, true);
            } else if (filter.to().isPresent()) {
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getEmployeeScores(toDate, false);
            } else {
                return activityRepository.getEmployeeScores();
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }

    public List<TeamScore> getTeamScore(ScoreFilter filter) {
        try {
            if (filter.from().isPresent() && filter.to().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getTeamScores(fromDate, toDate);
            } else if (filter.from().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                return activityRepository.getTeamScores(fromDate, true);
            } else if (filter.to().isPresent()) {
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getTeamScores(toDate, false);
            } else {
                return activityRepository.getTeamScores();
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }

    public List<DateCompanyScore> getDateCompanyScore(ScoreFilter filter) {
        Company company = authService.getAuthenticatedUser().getCompany();
        try {
            if (filter.from().isPresent() && filter.to().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getCompanyScores(company, fromDate, toDate);
            } else if (filter.from().isPresent()) {
                Date fromDate = dateFormat.parse(filter.from().get());
                return activityRepository.getCompanyScores(company, fromDate, true);
            } else if (filter.to().isPresent()) {
                Date toDate = dateFormat.parse(filter.to().get());
                return activityRepository.getCompanyScores(company, toDate, false);
            } else {
                return activityRepository.getCompanyScores(company);
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }
}
