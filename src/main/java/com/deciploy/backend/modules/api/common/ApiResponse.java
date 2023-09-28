package com.deciploy.backend.modules.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private boolean status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonProperty("status")
    public boolean getStatus() {
        return status;
    }

    public boolean setStatus(boolean status) {
        return this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(true);
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> data(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(true);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}
