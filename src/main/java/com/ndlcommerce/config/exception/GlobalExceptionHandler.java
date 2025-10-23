package com.ndlcommerce.config.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ndlcommerce.adapters.web.dto.ErrorFieldDTO;
import com.ndlcommerce.adapters.web.dto.ErrorResponseDTO;
import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.exception.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    System.err.println(e.getMessage());
    List<FieldError> fieldError = e.getFieldErrors();
    List<ErrorFieldDTO> erros =
        fieldError.stream()
            .map(fe -> new ErrorFieldDTO(fe.getField(), fe.getDefaultMessage()))
            .collect(Collectors.toList());

    return new ErrorResponseDTO(
        HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", erros);
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponseDTO handleBusinessException(BusinessException e) {
    return ErrorResponseDTO.notAcceptable(e.getMessage());
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponseDTO handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    return ErrorResponseDTO.conflict(e.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponseDTO handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Throwable cause = ex.getCause();
    String message = "Erro de leitura do JSON";

    // Caso seja um erro de enum inválido
    if (cause instanceof InvalidFormatException invalidEx) {
      JsonMappingException.Reference ref =
          invalidEx.getPath().isEmpty() ? null : invalidEx.getPath().get(0);
      String fieldName = (ref != null) ? ref.getFieldName() : "campo desconhecido";
      String invalidValue = invalidEx.getValue().toString();
      Class<?> targetType = invalidEx.getTargetType();

      // Se targetType for enum, podemos customizar
      if (targetType.isEnum()) {
        Object[] allowedValues = targetType.getEnumConstants();
        String allowed =
            java.util.Arrays.stream(allowedValues)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        message =
            String.format(
                "Valor inválido para o campo '%s': '%s'. Valores permitidos: [%s]",
                fieldName, invalidValue, allowed);
      } else {
        message = "Formato inválido no campo '" + fieldName + "'";
      }
    }

    return new ErrorResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), message, java.util.List.of());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDTO handleInvalidUUID(IllegalArgumentException e) {
    if (e.getMessage().contains("Invalid UUID string")) {
      return ErrorResponseDTO.badRequest(
          "ID com formato inesperado. Era esperado um UUID (xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)");
    }
    return ErrorResponseDTO.withErrors(e.getMessage(), List.of());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public ErrorResponseDTO handleNoSuchElementException(NoSuchElementException e) {
    System.err.println(e.getMessage());
    return new ErrorResponseDTO(
        HttpStatus.NOT_FOUND.value(), "Elemento com id fornecido não encontrado", List.of());
  }


  @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorResponseDTO handleValidationConstraintViolation(ConstraintViolationException e) {
    System.err.println(e.getMessage());

    List<ErrorFieldDTO> errors = e.getConstraintViolations().stream()
            .map(v -> new ErrorFieldDTO(
                    v.getPropertyPath().toString(),
                    v.getMessage()))
            .collect(Collectors.toList());

    return new ErrorResponseDTO(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Erro de validação",
            errors
    );
  }



  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDTO handleUnexpected(RuntimeException e) {
    System.err.println("Unhandled exception: " + e.getMessage());
    return new ErrorResponseDTO(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "🎉 Parabeeens🎉 você achou um erro não tratado! Por gentileza entre em contato com a mensagem e causa do erro: "
            + e.getMessage()
            + " | causa: "
            + e.getCause()
            + "localização: "
            + e.getLocalizedMessage(),
        List.of());
  }
}
