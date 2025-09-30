package com.ndlcommerce.entity.model;

import com.ndlcommerce.entity.UserType;

public interface User {
  boolean passwordIsValid();

  boolean nameIsValid();

  String getName();

  String getEmail();

  String getPassword();

  UserType getType();
}
