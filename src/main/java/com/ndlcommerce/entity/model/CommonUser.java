package com.ndlcommerce.entity.model;

import java.util.regex.Pattern;

public class CommonUser implements User {
  private String name;
  private String password;

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
  public String getPassword() {
    return this.password;
  }

  public CommonUser(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
