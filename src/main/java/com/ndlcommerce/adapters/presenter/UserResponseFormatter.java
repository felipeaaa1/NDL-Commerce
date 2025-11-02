package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.exception.UserAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.UserPresenter;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;

@Component
public class UserResponseFormatter implements UserPresenter {

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
    if ("existsByName".equals(error)) {
      throw new UserAlreadyExistsException("login já cadastrado");
    } else if ("existsByEmail".equals(error)) {
      throw new UserAlreadyExistsException("Email já cadastrado");
    } else if ("passwordIsValid".equals(error)) {
      throw new BusinessException(
          "Senha inválida, senha deve ter no mínimo 5 caracteres, uma letra maiúscula e uma minuscula");
    } else if ("NotFound".equals(error)) {
      throw new NoSuchElementException();
    } else if ("nameIsValid".equals(error)) {
      throw new BusinessException("Login inválido, login deve ter no mínimo 4 characteres");
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  public List<UserResponseDTO> prepareListSuccessView(List<UserResponseDTO> list) {
    return list;
  }
}
