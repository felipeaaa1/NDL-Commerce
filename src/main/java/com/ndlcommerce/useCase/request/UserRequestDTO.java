package com.ndlcommerce.useCase.request;

import com.ndlcommerce.entity.UserType;

public class UserRequestDTO {

  String login;
  String password;
  String email;
  UserType type;

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
