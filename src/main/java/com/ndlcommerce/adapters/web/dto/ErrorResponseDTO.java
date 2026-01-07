package com.ndlcommerce.adapters.web.dto;

import java.util.List;
import org.springframework.http.HttpStatus;

public record ErrorResponseDTO(int status, String message, List<ErrorFieldDTO> errors) {

  public static ErrorResponseDTO badRequest(String message) {
    return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, List.of());
  }

  public static ErrorResponseDTO conflict(String message) {
    return new ErrorResponseDTO(HttpStatus.CONFLICT.value(), message, List.of());
  }

  public static ErrorResponseDTO withErrors(String message, List<ErrorFieldDTO> errors) {
    return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), message, errors);
  }

  public static ErrorResponseDTO notAcceptable(String message) {
    return new ErrorResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), message, List.of());
  }

  public static ErrorResponseDTO forbidden(String message) {
    return new ErrorResponseDTO(HttpStatus.FORBIDDEN.value(), message, List.of());
  }

  public static ErrorResponseDTO locked(String message) {
    return new ErrorResponseDTO(HttpStatus.LOCKED.value(), message, List.of());
  }
}
