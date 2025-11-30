package com.ndlcommerce.useCase.request.user;

import com.ndlcommerce.entity.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

  @NotBlank
  @Size(min = 4, max = 50)
  String login;

  @NotBlank
  @Size(
      min = 5,
      message =
          "Senha inválida, senha deve ter no mínimo 5 caracteres, uma letra maiúscula e uma minuscula")
  String password;

  @NotBlank
  @Email(message = "E-mail inválido")
  String email;

  @NotNull UserType type;

  public UserRequestDTO(String login, UserType type, String password) {
    this.login = login;
    this.password = password;
    this.type = type;
  }

  public String getEmail() {
    return email;
  }

  public UserType getType() {
    return type;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }
}
