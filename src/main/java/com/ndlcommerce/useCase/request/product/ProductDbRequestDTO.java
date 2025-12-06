package com.ndlcommerce.useCase.request.product;

import java.util.Objects;
import lombok.Getter;

public class ProductDbRequestDTO {

  @Getter private String name;
  @Getter private String description;

  public ProductDbRequestDTO(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ProductDbRequestDTO that = (ProductDbRequestDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }
}
