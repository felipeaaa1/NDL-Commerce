package com.ndlcommerce.entity.model;

import com.ndlcommerce.entity.UserType;
import java.util.regex.Pattern;

public class CommonUser implements User {
  private String name;
  private String email;
  private String password;
  private UserType type;

  @Override
  public boolean passwordIsValid() {
    return password != null
        && password.length() > 5
        && Pattern.compile("[a-z]").matcher(password).find()
        && Pattern.compile("[A-Z]").matcher(password).find();
  }

  @Override
  public boolean nameIsValid() {
    return name != null && name.length() > 4;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public UserType getType() {
    return this.type;
  }

  public CommonUser(String name, String email, UserType type, String password) {
    this.name = name;
    this.email = email;
    this.type = type;
    this.password = password;
  }
}
