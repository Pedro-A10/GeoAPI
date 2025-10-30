package com.PedroA10.GeoAPI.exception;

public class InvalidJwtTokenException extends RuntimeException {
  public InvalidJwtTokenException(String message) {
    super(message);
  }
}
