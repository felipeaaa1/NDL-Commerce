package com.ndlcommerce.entity.model;

public interface User {
    boolean passwordIsValid();

    boolean nameIsValid();

    String getName();

    String getPassword();
}
