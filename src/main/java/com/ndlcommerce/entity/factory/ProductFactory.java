package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.Product;

public interface ProductFactory {

  Product create(String name, String description);
}
