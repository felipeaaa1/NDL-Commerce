package com.ndlcommerce.useCase.request;

public class UserResponseDTO {
  private String login;
  private String email;
  private String creationTime;

  public UserResponseDTO() {}

  public UserResponseDTO(String login, String email, String creationTime) {
    this.login = login;
    this.email = email;
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
