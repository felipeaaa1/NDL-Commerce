package com.ndlcommerce.entity;

public enum UserType {
  COMMON("common"),
  ADMIN("admin");

  private final String type;

  UserType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
