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
import java.util.Optional;

@Service
public class ScreenshotService {
    final DateFormat dateFormat;
    @Autowired
    private AuthService authService;
    @Autowired
    private ScreenshotRepository repository;

    public ScreenshotService() {
        dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);
    }

    public void save(ScreenshotRequest screenshotRequests) {
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

    public List<Screenshot> findByUser(String userId, Optional<String> from, Optional<String> to) {
        try {
            if (from.isPresent() && to.isEmpty()) {
                return repository.findByUserIdAndCapturedAtAfter(userId, dateFormat.parse(from.get()));
            }

            if (to.isPresent() && from.isEmpty()) {
                return repository.findByUserIdAndCapturedAtBefore(userId, dateFormat.parse(to.get()));
            }

            if (from.isPresent() && to.isPresent()) {
                return repository.findByUserIdBetween(userId, dateFormat.parse(from.get()), dateFormat.parse(to.get()));
            }


        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        return repository.findScreenshotByUserId(userId);
    }
}
