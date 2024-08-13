package com.deciploy.backend.modules.api.tracking;


import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.tracking.dto.ActivityFilter;
import com.deciploy.backend.modules.api.tracking.dto.TimeFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tracking", description = "Tracking data  API")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("api/tracking")
@Secured({"MANAGER"})
public class TrackingController {
    @Autowired
    private TrackingService trackingService;

    @GetMapping("activity")
    public ResponseEntity getTracking(ActivityFilter filter) {
        return ApiResponse.data(trackingService.getTracking(filter));
    }

    @GetMapping("time")
    public ResponseEntity getTracking(TimeFilter filter) {
        return ApiResponse.data(trackingService.getTimes(filter));
    }
}
