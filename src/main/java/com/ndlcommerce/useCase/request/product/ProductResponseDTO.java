package com.ndlcommerce.useCase.request.product;

import java.util.UUID;

public class ProductResponseDTO {

  private UUID uuid;
  private String name;
  private String description;
  private String createdAt;

  public ProductResponseDTO() {}

  public ProductResponseDTO(UUID uuid, String name, String description, String createdAt) {
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.createdAt = createdAt;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
