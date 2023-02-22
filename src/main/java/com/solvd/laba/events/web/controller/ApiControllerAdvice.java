package com.solvd.laba.events.web.controller;

import com.solvd.laba.events.domain.exception.*;
import com.solvd.laba.events.web.dto.ResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(ex.getMessage());
        return new ResponseDto(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(IllegalTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleIllegalTimeException(IllegalTimeException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handlePasswordMismatchException(PasswordMismatchException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleAuthenticationException(AuthenticationException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(MinioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMinioException(MinioException ex) {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMultipartException(MultipartException ex) throws IOException {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDto handleConstraintViolationException(ConstraintViolationException ex) throws IOException {
        log.error(ex.getMessage());
        return new ResponseDto(List.of(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto handleException(Exception ex) {
        log.error(ex.getMessage(), ex.getCause());
        return new ResponseDto(List.of(ex.getMessage()));
    }

}