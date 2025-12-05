package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.Product;
import java.util.UUID;

public interface ProductFactory {

  Product create(UUID id, String name, String description);
}
