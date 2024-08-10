package com.deciploy.backend.modules.api.weigtage;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.weigtage.dto.WeightageSaveRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/weightage")
@Secured("MANAGER")
@Tag(name = "Weightage", description = "Weightage API applications for weightage calculation")
@SecurityRequirement(name = "Bearer Authentication")
public class WeightageController {
    @Autowired
    private WeightageService weightageService;

    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody List<WeightageSaveRequest> requests) {
        weightageService.save(requests);
        return ApiResponse.success("Weightage saved successfully");
    }

    @GetMapping()
    public ResponseEntity get() {
        return ApiResponse.data(weightageService.getTeamWeightages());
    }
}
