package com.ndlcommerce.entity.factory.implementation;

import com.ndlcommerce.entity.factory.interfaces.ProductFactory;
import com.ndlcommerce.entity.model.implementation.CommonProduct;
import com.ndlcommerce.entity.model.interfaces.Product;

public class CommonProductFactoryImp implements ProductFactory {

  @Override
  public Product create(String name, String description) {
    return new CommonProduct(name, description);
  }
}
