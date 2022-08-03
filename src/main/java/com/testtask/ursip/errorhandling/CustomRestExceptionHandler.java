package com.testtask.ursip.errorhandling;

import com.testtask.ursip.exception.TaskException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    // 500
    @ExceptionHandler({ TaskException.class })
    public ResponseEntity<Object> handleAll(final TaskException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ((ServletWebRequest)request).getRequest().getRequestURI(), ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
