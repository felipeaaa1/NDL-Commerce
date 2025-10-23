package com.ndlcommerce.useCase.request;

import com.ndlcommerce.entity.UserType;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserDbRequestDTO {

  private final String login;
  private final String email;
  private final UserType type;
  private String password;
  private LocalDateTime creationTime;

  public UserDbRequestDTO(String login, String email, UserType type, String password) {
    this.login = login;
    this.email = email;
    this.type = type;
    this.password = password;
  }

  public UserDbRequestDTO(String login, String email, UserType type) {
    this.login = login;
    this.email = email;
    this.type = type;
  }

  public String getLogin() {
    return login;
  }

  public String getEmail() {
    return email;
  }

  public UserType getType() {
    return type;
  }

  public String getPassword() {
    return password;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserDbRequestDTO)) return false;
    UserDbRequestDTO that = (UserDbRequestDTO) o;
    return Objects.equals(login, that.login)
        && Objects.equals(password, that.password)
        && Objects.equals(type, that.type)
        && Objects.equals(email, that.email)
        && Objects.equals(creationTime, that.creationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login, password, creationTime);
  }
}
