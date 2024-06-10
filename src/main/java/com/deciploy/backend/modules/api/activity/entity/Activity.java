package com.deciploy.backend.modules.api.activity.entity;

import com.deciploy.backend.modules.api.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Application application;

    private String name;

    private String title;

    private Date startTime;

    private Date endTime;

    @CreationTimestamp
    private Date syncTime;
}
