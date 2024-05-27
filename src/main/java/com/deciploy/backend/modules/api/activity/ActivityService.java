package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.dto.ActivitySyncRequest;
import com.deciploy.backend.modules.api.activity.entity.Activity;
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

@Service
public class ActivityService {
    @Autowired
    private AuthService authService;

    @Autowired
    private ActivityRepository activityRepository;

    public void sync(List<ActivitySyncRequest> activitySyncRequests) {
        final DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);

        try {
            for (ActivitySyncRequest activitySyncRequest : activitySyncRequests) {
                Activity activity = Activity.builder()
                        .name(activitySyncRequest.name())
                        .title(activitySyncRequest.title())
                        .startTime(dateFormat.parse(activitySyncRequest.startTime()))
                        .endTime(dateFormat.parse(activitySyncRequest.endTime()))
                        .user(authService.getAuthenticatedUser())
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

}
