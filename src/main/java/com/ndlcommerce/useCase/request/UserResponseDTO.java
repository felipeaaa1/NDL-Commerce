package com.ndlcommerce.useCase.request;

public class UserResponseDTO {
  private String login;
  private String email;
  private String type;
  private String creationTime;

  public UserResponseDTO() {}

  public UserResponseDTO(String login, String email, String type, String creationTime) {
    this.login = login;
    this.email = email;
    this.type = type;
    this.creationTime = creationTime;
  }

  public String getLogin() {
    return login;
  }

  public String getEmail() {
    return email;
  }

  public String getType() {
    return type;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }
}
