package com.deciploy.backend.modules.api.application;

import com.deciploy.backend.modules.api.common.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/application")
@Secured("EMPLOYEE")
@Tag(name = "Application", description = "Application API for tracking")
@SecurityRequirement(name = "Bearer Authentication")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping()
    public ResponseEntity getApplications() {
        return ApiResponse.data(applicationService.getApplications(Optional.empty()));
    }

    @GetMapping("{type}")
    public ResponseEntity getApplications(@PathVariable Optional<String> type) {
        return ApiResponse.data(applicationService.getApplications(type));
    }

    @GetMapping("types")
    public ResponseEntity getApplicationTypes() {
        return ApiResponse.data(applicationService.getApplicationTypes());
    }
}
