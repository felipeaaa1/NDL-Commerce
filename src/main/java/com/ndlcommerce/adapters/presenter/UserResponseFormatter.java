package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.useCase.UserPresenter;
import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.exception.UserAlreadyExistsException;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserResponseFormatter implements UserPresenter {

  @Override
  public UserResponseDTO prepareSuccessView(UserResponseDTO response) {
    LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
    response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    return response;
  }

  @Override
  public UserResponseDTO prepareFailView(String error) {
    if ("existsByName".equals(error)) {
      throw new UserAlreadyExistsException("Usuário ja existe");
    } else if ("passwordIsValid".equals(error)) {
      throw new BusinessException(
          "Senha inválida, senha deve ter no mínimo 5 caracteres, uma letra maiúscula e uma minuscula");
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  public List<UserResponseDTO> prepareListSuccessView(List<UserResponseDTO> list) {
    return list;
  }
}
