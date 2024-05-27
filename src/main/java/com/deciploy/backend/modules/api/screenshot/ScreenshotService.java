package com.deciploy.backend.modules.api.screenshot;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.screenshot.dto.ScreenshotRequest;
import com.deciploy.backend.modules.api.screenshot.entity.Screenshot;
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
public class ScreenshotService {
    @Autowired
    private AuthService authService;

    @Autowired
    private ScreenshotRepository repository;

    public void save(ScreenshotRequest screenshotRequests) {
        final DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);

        try {
            Screenshot screenshot = Screenshot.builder()
                    .capturedAt(dateFormat.parse(screenshotRequests.capturedAt()))
                    .url(screenshotRequests.url())
                    .user(authService.getAuthenticatedUser())
                    .build();
            repository.save(screenshot);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }

    public List<Screenshot> findByUser(String userId) {
        return repository.findScreenshotByUserId(userId);
    }

}
