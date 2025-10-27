package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.UserType;
import com.ndlcommerce.entity.model.User;

public interface UserFactory {
  User create(String login, String email, UserType type, String password);
}
