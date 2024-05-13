package com.deciploy.backend.modules.api.company.entity;

import com.deciploy.backend.modules.api.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private String address;

    private String phone;

    @JsonIgnore
    @ManyToOne
    private User createdBy;
}
