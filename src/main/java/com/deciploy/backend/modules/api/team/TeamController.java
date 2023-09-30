package com.deciploy.backend.modules.api.team;

import com.deciploy.backend.modules.api.common.ApiResponse;
import com.deciploy.backend.modules.api.team.dto.CreateTeamRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/team")
@Tag(name = "Team", description = "Team API for create and manage team")
@SecurityRequirement(name = "Bearer Authentication")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody CreateTeamRequest createTeamRequest) {
        teamService.create(createTeamRequest);
        return ApiResponse.success("Team created successfully");
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ApiResponse.data(teamService.getAll());
    }
}
