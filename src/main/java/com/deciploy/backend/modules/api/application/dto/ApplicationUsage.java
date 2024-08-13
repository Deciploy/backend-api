package com.deciploy.backend.modules.api.application.dto;

import com.deciploy.backend.modules.api.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUsage {
    private Application application;
    private Double usage;
}
