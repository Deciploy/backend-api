package com.deciploy.backend.modules.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponseEntity<T> {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResponseEntity<ApiResponseEntity<T>> success(String message) {
        ApiResponseEntity<T> response = new ApiResponseEntity<>();
        response.setStatus(true);
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponseEntity<T>> data(T data) {
        ApiResponseEntity<T> response = new ApiResponseEntity<>();
        response.setStatus(true);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}