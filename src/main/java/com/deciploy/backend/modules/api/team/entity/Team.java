package com.deciploy.backend.modules.api.team.entity;

import com.deciploy.backend.modules.api.company.entity.Company;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    @ManyToOne
    private Company company;
}
