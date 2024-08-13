package com.deciploy.backend.modules.api.weigtage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class WeightageBulkSaveRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<WeightageSaveRequest> weightages;
}
