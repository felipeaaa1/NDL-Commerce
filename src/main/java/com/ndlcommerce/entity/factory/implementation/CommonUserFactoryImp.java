package com.ndlcommerce.entity.factory.implementation;

import com.ndlcommerce.entity.enums.UserType;
import com.ndlcommerce.entity.factory.interfaces.UserFactory;
import com.ndlcommerce.entity.model.implementation.CommonUser;
import com.ndlcommerce.entity.model.interfaces.User;

public class CommonUserFactoryImp implements UserFactory {
  @Override
  public User create(String login, String email, UserType type, String password) {
    CommonUser user = new CommonUser(login, email, type, password);
    return user;
  }
}
