package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.exception.BusinessException;
import com.ndlcommerce.exception.EntityAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.user.UserPresenter;
import com.ndlcommerce.useCase.request.user.UserResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

@Component
public class UserResponseFormatter implements UserPresenter {
  private static final Map<String, RuntimeException> ERRORS =
      Map.of(
          "existsByName", new EntityAlreadyExistsException("login já cadastrado"),
          "existsByEmail", new EntityAlreadyExistsException("Email já cadastrado"),
          "passwordIsValid",
              new BusinessException(
                  "Senha inválida, senha deve ter no mínimo 5 caracteres, uma letra maiúscula e uma minuscula"),
          "NotFound", new NoSuchElementException(),
          "nameIsValid",
              new BusinessException("Login inválido, login deve ter no mínimo 4 characteres"));

  @Override
  public UserResponseDTO prepareSuccessView(UserResponseDTO response) {
    if (response != null) {
      LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
      response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
      return response;
    } else {
      return null;
    }
  }

  @Override
  public UserResponseDTO prepareFailView(String error) {
    RuntimeException exception = ERRORS.get(error);
    if (exception != null) {
      throw exception;
    }

    throw new RuntimeException("Erro desconhecido: " + error);
  }

  @Override
  public List<UserResponseDTO> prepareListSuccessView(List<UserResponseDTO> list) {
    return list;
  }
}
