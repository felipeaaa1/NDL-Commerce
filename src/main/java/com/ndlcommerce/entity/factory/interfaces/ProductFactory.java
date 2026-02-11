package com.ndlcommerce.entity.factory.interfaces;

import com.ndlcommerce.entity.model.interfaces.Product;

public interface ProductFactory {

  Product create(String name, String description);
}
