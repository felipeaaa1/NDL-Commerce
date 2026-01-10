package com.ndlcommerce.useCase.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CustomerRequestDTO {

  @NotNull UUID userID;

  @Size(min = 4, max = 50, message = "Nome do Cliente não é válido. Nome deve ter entre 4 e 50 caracteres.")
  @NotNull(message = "Nome do Cliente não é válido. Nome deve ter entre 4 e 50 caracteres.") String name;

  @NotNull(message = "tamanho deve ser entre 4 e 50") String contact;

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
