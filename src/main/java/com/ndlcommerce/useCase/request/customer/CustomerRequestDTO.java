package com.ndlcommerce.useCase.request.customer;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CustomerRequestDTO {

  @NotNull UUID userID;

  @NotNull String name;

  @NotNull String contact;

  @NotNull String address;

  public CustomerRequestDTO(UUID userID, String name, String contact, String address) {
    this.userID = userID;
    this.name = name;
    this.contact = contact;
    this.address = address;
  }

  public UUID getUserID() {
    return userID;
  }

  public String getName() {
    return name;
  }

  public String getContact() {
    return contact;
  }

  public String getAddress() {
    return address;
  }
}
