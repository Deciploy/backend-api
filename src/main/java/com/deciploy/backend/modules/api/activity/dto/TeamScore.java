package com.deciploy.backend.modules.api.activity.dto;

import com.deciploy.backend.modules.api.team.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamScore {
    private Team team;
    private int score;
}
