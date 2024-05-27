package com.deciploy.backend.modules.api.activity;

import com.deciploy.backend.modules.api.activity.dto.ActivitySyncRequest;
import com.deciploy.backend.modules.api.common.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activity")
@Secured("EMPLOYEE")
@Tag(name = "Activity", description = "Activity API for sync and manage activity")
@SecurityRequirement(name = "Bearer Authentication")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping()
    public ResponseEntity sync(@Valid @RequestBody List<ActivitySyncRequest> activitySyncRequests) {
        activityService.sync(activitySyncRequests);

        return ApiResponse.success("Activity synced successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity findByUser(@PathVariable String userId) {
        return ApiResponse.data(activityService.findByUser(userId));
    }
}
