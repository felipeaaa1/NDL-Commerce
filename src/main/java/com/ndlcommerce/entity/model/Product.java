package com.ndlcommerce.entity.model;

import java.util.UUID;

public interface Product {

  UUID getId();

  String getName();

  String getDescription();

  boolean nameIsValid();

  boolean descriptionIsValid();
}
