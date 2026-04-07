package com.serfinsa.backend.dto;

import lombok.Data;

@Data
public class ApiLogin<T, N> {
    private boolean success;
    private T data;
    private N accessToken;
    private String message;

    public ApiLogin(boolean success, T data, N accessToken, String message){
        this.success = success;
        this.data = data;
        this.accessToken = accessToken;
        this.message = message;
    }

    public static <T, N> ApiLogin<T, N> success(T data, N accessToken, String message){
        return new ApiLogin<>(true,data,accessToken,message);
    }

    public static <T, N> ApiLogin<T, N> error(String message){
        return new ApiLogin<>(false,null,null,message);
    }
}
