package com.ndlcommerce.entity.model;

public interface Product {

  String getName();

  String getDescription();

  boolean nameIsValid();

  boolean descriptionIsValid();
}
