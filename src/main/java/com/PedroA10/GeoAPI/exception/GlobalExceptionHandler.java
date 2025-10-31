package com.PedroA10.GeoAPI.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Map<Class<? extends Throwable>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
    UserNotFoundException.class, HttpStatus.NOT_FOUND,
    PointNotFoundException.class, HttpStatus.NOT_FOUND,
    InvalidCredentialsException.class, HttpStatus.UNAUTHORIZED,
    InvalidJwtTokenException.class, HttpStatus.UNAUTHORIZED,
    EmailAlreadyExistsException.class, HttpStatus.CONFLICT
  );

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, Throwable ex, HttpServletRequest request) {
    ErrorResponse error = new ErrorResponse(
      LocalDateTime.now(),
      status.value(),
      status.getReasonPhrase(),
      ex.getMessage() + " | Path: " + request.getRequestURI()
    );
    return new ResponseEntity<>(error, status);
  }
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions(Throwable ex, HttpServletRequest request) {
    HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    return buildResponse(status, ex, request);
  }
}
