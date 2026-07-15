package com.tesdb.learning.core.handler;

import com.tesdb.learning.core.exception.BadRequestException;
import com.tesdb.learning.core.exception.ForbiddenException;
import com.tesdb.learning.core.exception.ResourceNotFoundException;
import com.tesdb.learning.core.exception.UnauthorizedException;
import com.tesdb.learning.core.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request)
    {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"BAD_REQUEST", request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex, HttpServletRequest request)
    {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(),"UNAUTHORIZED", request);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex, HttpServletRequest request)
    {
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage(),"FORBIDDEN", request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request)
    {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(),"RESOURCE_NOT_FOUND", request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request)
    {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return buildResponse(HttpStatus.BAD_REQUEST, message, request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request)
    {

        log.error("Unhandled exception occurred.", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please contact the administrator.","INTERNAL_SERVER_ERROR", request);
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request)
    {

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, String errorCode, HttpServletRequest request)
    {

        ErrorResponse response = ErrorResponse.builder()
                .success(false)
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .errorCode(errorCode)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(response);

    }

}