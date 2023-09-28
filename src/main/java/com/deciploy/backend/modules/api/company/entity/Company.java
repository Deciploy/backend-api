package com.deciploy.backend.modules.api.company.entity;

import com.deciploy.backend.modules.api.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private User createdBy;
}
