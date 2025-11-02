package com.ndlcommerce.entity.model;

import java.util.UUID;

public class CommonCustomer implements Customer {

  private final UUID userId;
  private final String name;
  private final String contact;
  private final String address;
  private final boolean custumerActive;
  private final boolean userActive;

  public CommonCustomer(
      UUID userId,
      String name,
      String contact,
      String address,
      boolean custumerActive,
      boolean userActive) {
    this.userId = userId;
    this.name = name;
    this.contact = contact;
    this.address = address;
    this.custumerActive = custumerActive;
    this.userActive = userActive;
  }

  @Override
  public boolean nameIsValid() {
    return this.name != null && !this.name.isBlank() && this.name.length() >= 4;
  }

  @Override
  public boolean hasActiveUser() {
    return this.userActive;
  }

  @Override
  public boolean hasAddress() {
    return this.address != null && !this.address.isBlank();
  }

  @Override
  public boolean isCustumerActive() {
    return this.custumerActive;
  }

  @Override
  public UUID getUserId() {
    return this.userId;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getContact() {
    return this.contact;
  }

  @Override
  public String getAddress() {
    return this.address;
  }
}
