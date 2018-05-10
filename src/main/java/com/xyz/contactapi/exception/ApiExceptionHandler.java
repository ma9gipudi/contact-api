package com.xyz.contactapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception e) {
       return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
