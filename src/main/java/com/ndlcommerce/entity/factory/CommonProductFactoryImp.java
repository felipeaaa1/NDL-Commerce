package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.CommonProduct;
import com.ndlcommerce.entity.model.Product;

public class CommonProductFactoryImp implements ProductFactory {

  @Override
  public Product create(String name, String description) {
    return new CommonProduct(name, description);
  }
}
