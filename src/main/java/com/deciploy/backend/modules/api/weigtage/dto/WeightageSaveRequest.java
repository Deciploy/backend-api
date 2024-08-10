package com.deciploy.backend.modules.api.weigtage.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record WeightageSaveRequest(
        @NotBlank
        String teamId,

        @NotBlank
        String applicationTypeId,

        @Min(0)
        @Max(10)
        int weightage
) {
}
