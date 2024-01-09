package org.workintech.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(EcommerceException ecommerceException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ecommerceException.getMessage());
        log.error("Ecommerce exception occurred " + exceptionResponse.getMessage());
        return new ResponseEntity<>(exceptionResponse, ecommerceException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException exception) {
        List<Map<String, String>> errorList = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                {
                    System.out.println();
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    log.error("Exception occurred: " + fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();

        return new ResponseEntity(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        log.error("Exception occurred " + exceptionResponse.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
