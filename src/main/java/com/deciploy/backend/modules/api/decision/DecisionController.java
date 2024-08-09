package com.deciploy.backend.modules.api.decision;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.decision.dto.WorkTimeFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/decision")
@Secured("MANAGER")
@Tag(name = "Decision", description = "Decision support API")
@SecurityRequirement(name = "Bearer Authentication")
public class DecisionController {
    @Autowired
    private DecisionService decisionService;

    @GetMapping("work-times")
    public ResponseEntity getAllEmployeesWorkTimes(WorkTimeFilter filter) {
        return ApiResponse.data(decisionService.getAllEmployeesWorkTimes(filter));
    }
}
