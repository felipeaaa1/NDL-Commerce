package com.ndlcommerce.entity.model.interfaces;

import com.ndlcommerce.entity.enums.UserType;

public interface User {
  boolean passwordIsValid();

  boolean loginIsValid();

  String getLogin();

  String getEmail();

  String getPassword();

  UserType getType();
}
