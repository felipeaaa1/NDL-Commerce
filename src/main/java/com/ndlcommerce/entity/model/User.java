package com.ndlcommerce.entity.model;

import com.ndlcommerce.entity.UserType;

public interface User {
  boolean passwordIsValid();

  boolean loginIsValid();

  String getLogin();

  String getEmail();

  String getPassword();

  UserType getType();
}
