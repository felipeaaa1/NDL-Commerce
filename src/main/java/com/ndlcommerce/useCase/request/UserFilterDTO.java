package com.ndlcommerce.useCase.request;

import com.ndlcommerce.entity.enums.UserType;

public class UserFilterDTO {

  String login;
  String email;
  UserType type;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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

  public UserFilterDTO() {}

  public UserFilterDTO(String login, String email, String type) {
    this.login = login;
    this.email = email;
    this.type = UserType.valueOf(type);
  }
}
