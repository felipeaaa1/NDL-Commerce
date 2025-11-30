package com.ndlcommerce.useCase.request.customer;

import java.util.UUID;

public class CustomerResponseDTO {

  private UUID uuid;
  private String userLogin;
  private String name;
  private String contact;
  private String creationTime;

  public CustomerResponseDTO() {}

  public CustomerResponseDTO(
      UUID uuid, String userLogin, String name, String contact, String creationTime) {
    this.uuid = uuid;
    this.userLogin = userLogin;
    this.name = name;
    this.contact = contact;
    this.creationTime = creationTime;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public String getName() {
    return name;
  }

  public String getContact() {
    return contact;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }
}
