package com.deciploy.backend.modules.api.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateCompanyScore {
    private Date date;
    private int score;
}
