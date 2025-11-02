package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.Customer;
import java.util.UUID;

public interface CustomerFactory {
  Customer create(
      UUID userId,
      String name,
      String contact,
      String address,
      boolean custumerActive,
      boolean userActive);
}
