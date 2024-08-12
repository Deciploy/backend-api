package com.deciploy.backend.modules.api.weigtage.entity;

import com.deciploy.backend.modules.api.application.entity.ApplicationType;
import com.deciploy.backend.modules.api.team.entity.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationTypeWeightage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private ApplicationType applicationType;

    @ManyToOne
    private Team team;

    private int weightage;
}
