package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.dto.ActivitySyncRequest;
import com.deciploy.backend.modules.api.activity.dto.EmployeeScore;
import com.deciploy.backend.modules.api.activity.dto.TeamScore;
import com.deciploy.backend.modules.api.activity.entity.Activity;
import com.deciploy.backend.modules.api.activity.repository.ActivityRepository;
import com.deciploy.backend.modules.api.application.ApplicationService;
import com.deciploy.backend.modules.api.application.entity.Application;
import com.deciploy.backend.modules.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);
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

    public List<EmployeeScore> getEmployeeScore() {
        return activityRepository.getEmployeeScores();
    }

    public List<TeamScore> getTeamScore() {
        return activityRepository.getTeamScores();
    }
}
