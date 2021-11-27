package com.vatit.todo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final String DEFAULT_MESSAGE = "Oops something went wrong";
    private final String ILLEGAL_NAME_MESSAGE = "'To-Do' not allowed";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleUserException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(DEFAULT_MESSAGE);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalToDoNameException.class)
    public final ResponseEntity<Object> handleIllegalToDoName(IllegalToDoNameException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ILLEGAL_NAME_MESSAGE);
        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
