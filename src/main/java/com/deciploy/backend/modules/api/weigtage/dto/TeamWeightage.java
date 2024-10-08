package com.deciploy.backend.modules.api.weigtage.dto;

import com.deciploy.backend.modules.api.team.entity.Team;
import com.deciploy.backend.modules.api.weigtage.entity.ApplicationTypeWeightage;

import java.util.List;

public record TeamWeightage(
        Team team,

        List<ApplicationTypeWeightage> weightages
) {
}
