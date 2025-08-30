package com.ndlcommerce.useCase.request;

public class UserResponseModel {
  private String login;
  private String creationTime;

  public UserResponseModel() {}

  public UserResponseModel(String login, String creationTime) {
    this.login = login;
    this.creationTime = creationTime;
  }

  public String getLogin() {
    return login;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }
}
