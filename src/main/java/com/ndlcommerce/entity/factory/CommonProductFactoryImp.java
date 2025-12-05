package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.CommonProduct;
import com.ndlcommerce.entity.model.Product;
import java.util.UUID;

public class CommonProductFactoryImp implements ProductFactory {

  @Override
  public Product create(UUID id, String name, String description) {
    return new CommonProduct(id, name, description);
  }
}
