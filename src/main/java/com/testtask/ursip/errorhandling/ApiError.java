package com.testtask.ursip.errorhandling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError {

    private HttpStatus status;
    private String path;
    private List<String> errors;


    public ApiError(HttpStatus status, String path, List<String> errors) {
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public ApiError(HttpStatus status, String path, String error) {
        this.status = status;
        this.path = path;
        errors = Arrays.asList(error);
    }
}
