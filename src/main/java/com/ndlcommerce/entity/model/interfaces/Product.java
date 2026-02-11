package com.ndlcommerce.entity.model.interfaces;

public interface Product {

  String getName();

  String getDescription();

  boolean nameIsValid();

  boolean descriptionIsValid();
}
