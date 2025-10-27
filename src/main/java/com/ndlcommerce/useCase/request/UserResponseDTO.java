package com.ndlcommerce.useCase.request;

import java.util.UUID;

public class UserResponseDTO {
  private UUID uuid;
  private String login;
  private String email;
  private String type;
  private String creationTime;

  public UserResponseDTO() {}

  public UserResponseDTO(UUID uuid, String login, String email, String type, String creationTime) {
    this.uuid = uuid;
    this.login = login;
    this.email = email;
    this.type = type;
    this.creationTime = creationTime;
  }

  public UUID getUUID() {
    return uuid;
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
