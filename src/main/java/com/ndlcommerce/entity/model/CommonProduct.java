package com.ndlcommerce.entity.model;

import java.util.UUID;

public class CommonProduct implements Product {

  private final UUID id;
  private final String name;
  private final String description;

  public CommonProduct(UUID id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  @Override
  public UUID getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public boolean nameIsValid() {
    return this.name != null
        && !this.name.isBlank()
        && this.name.length() >= 3
        && this.name.length() < 200;
  }

  @Override
  public boolean descriptionIsValid() {
    return this.description != null
        && this.description.length() > 5
        && this.description.length() <= 200;
  }
}
