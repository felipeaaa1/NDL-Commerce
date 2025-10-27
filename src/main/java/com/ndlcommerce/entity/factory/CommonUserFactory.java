package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.UserType;
import com.ndlcommerce.entity.model.CommonUser;
import com.ndlcommerce.entity.model.User;

public class CommonUserFactory implements UserFactory {
  @Override
  public User create(String login, String email, UserType type, String password) {
    CommonUser user = new CommonUser(login, email, type, password);
    return user;
  }
}
