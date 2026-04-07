package com.serfinsa.backend.controller;

import com.serfinsa.backend.dto.ApiLogin;
import com.serfinsa.backend.dto.ApiResponse;
import com.serfinsa.backend.exception.ResourceBadRequestException;
import com.serfinsa.backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handlerNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handlerBadRequest(ResourceBadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiLogin<Void,Void>> handlerUnauthorized(AuthenticationException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiLogin.error("Las credenciales no son validas."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handlerInternalError(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Ocurrio un error indesperado en el servidor. " + ex.getMessage()));
    }

}
