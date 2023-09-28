package com.deciploy.backend.modules.api.company.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private String address;

    private String contactNumber;

    private String contactPerson;
}
