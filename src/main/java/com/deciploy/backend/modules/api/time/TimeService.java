package com.deciploy.backend.modules.api.time;

import com.deciploy.backend.modules.api.auth.AuthService;
import com.deciploy.backend.modules.api.time.entity.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class TimeService {
    private final DateFormat dateFormat;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private AuthService authService;

    public TimeService() {
        dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);
    }

    public void clockIn(String clockIn) {
        try {
            Time time = Time.builder()
                    .clockIn(dateFormat.parse(clockIn))
                    .user(authService.getAuthenticatedUser())
                    .build();

            timeRepository.save(time);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }

    public void clockOut(String clockOut) {
        try {
            Time time = timeRepository.getClockedInTimeToClockOutTime(authService.getAuthenticatedUser().getId())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No clocked in time found"));

            time.setClockOut(dateFormat.parse(clockOut));

            timeRepository.save(time);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    }
}
