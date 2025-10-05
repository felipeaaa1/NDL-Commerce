package com.ndlcommerce.useCase.request;

import com.ndlcommerce.entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

  @NotBlank
  @Size(min = 3, max = 50)
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

  public void setEmail(String email) {
    this.email = email;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
