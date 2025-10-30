package com.PedroA10.GeoAPI.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private ResponseEntity<ErrorResponse> responseBuild(HttpStatus status, String message, String path) {
    ErrorResponse error = new ErrorResponse(LocalDateTime.now(), status.value(), message, path);
    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler({UserNotFoundException.class, PointNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException e, HttpServletRequest request) {
    return responseBuild(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler({InvalidCredentialsException.class, InvalidJwtTokenException.class})
  public ResponseEntity<ErrorResponse> handleUnauthorized(RuntimeException e, HttpServletRequest request) {
    return responseBuild(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleConflict(EmailAlreadyExistsException e, HttpServletRequest request) {
    return responseBuild(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneric(Exception e, HttpServletRequest request) {
    return responseBuild(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
  }
}
