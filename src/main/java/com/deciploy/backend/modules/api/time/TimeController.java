package com.deciploy.backend.modules.api.time;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.time.dto.ClockInRequest;
import com.deciploy.backend.modules.api.time.dto.ClockOutRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/time")
@Secured("EMPLOYEE")
@Tag(name = "Time", description = "Time API for clock in and clock out")
@SecurityRequirement(name = "Bearer Authentication")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @PostMapping("/clock-in")
    public ResponseEntity clockIn(@Valid @RequestBody ClockInRequest request) {
        timeService.clockIn(request.time());
        return ApiResponse.success("Clocked in successfully");
    }

    @PostMapping("/clock-out")
    public ResponseEntity clockOut(@Valid @RequestBody ClockOutRequest request) {
        timeService.clockOut(request.time());
        return ApiResponse.success("Clocked out successfully");
    }
}
