package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.CommonUser;
import com.ndlcommerce.entity.model.User;

public class CommonUserFactory implements UserFactory {
  @Override
  public User create(String name, String password) {
    return new CommonUser(name, password);
  }
}
