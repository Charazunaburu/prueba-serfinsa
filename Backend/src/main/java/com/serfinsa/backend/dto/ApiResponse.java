package com.serfinsa.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(boolean success, String message, T data){
        this.message = message;
        this.data = data;
        this.success = success;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data, String message){
        return new ApiResponse<>(true, message,data);
    }

    public static <T> ApiResponse<T> error(String message){
        return new ApiResponse<>(false, message,null);
    }


}
