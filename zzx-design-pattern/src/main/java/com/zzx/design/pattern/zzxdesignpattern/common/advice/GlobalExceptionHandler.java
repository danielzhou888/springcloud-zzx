package com.zzx.design.pattern.zzxdesignpattern.common.advice;

import com.zzx.design.pattern.zzxdesignpattern.common.base.ApiResponse;
import com.zzx.design.pattern.zzxdesignpattern.common.exception.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 修改为具体的泛型类型
    public static <T> ResponseEntity<ApiResponse<T>> errorResponseEntity(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>("ERROR", message, null);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<ApiResponse<String>> handleCommandExecutionException(CommandExecutionException e) {
        return errorResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<String>> handleBindException(BindException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return errorResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return errorResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        return errorResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        return errorResponseEntity("系统内部错误：" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}