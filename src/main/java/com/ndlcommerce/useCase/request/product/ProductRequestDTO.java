package com.ndlcommerce.useCase.request.product;

import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
  @NotNull private String name;

  @NotNull private String description;

  public ProductRequestDTO(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
