package com.deciploy.backend.modules.api.time.entity;

import com.deciploy.backend.modules.api.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Date clockIn;

    private Date clockOut;

    @ManyToOne
    private User user;
}
