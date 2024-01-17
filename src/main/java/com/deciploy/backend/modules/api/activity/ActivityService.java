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
            for (ActivitySyncRequest activitySyncRequest :
                    activitySyncRequests) {
                Activity activity = new Activity();
                activity.setName(activitySyncRequest.name());
                activity.setTitle(activitySyncRequest.title());
                activity.setStartTime(dateFormat.parse(activitySyncRequest.startTime()));
                activity.setEndTime(dateFormat.parse(activitySyncRequest.endTime()));
                activity.setUser(authService.getAuthenticatedUser());

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
