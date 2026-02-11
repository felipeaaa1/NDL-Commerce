package com.ndlcommerce.entity.factory.interfaces;

import com.ndlcommerce.entity.enums.UserType;
import com.ndlcommerce.entity.model.interfaces.User;

public interface UserFactory {
  User create(String login, String email, UserType type, String password);
}
