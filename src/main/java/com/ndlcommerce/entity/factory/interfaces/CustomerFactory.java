package com.ndlcommerce.entity.factory.interfaces;

import com.ndlcommerce.entity.model.interfaces.Customer;
import java.util.UUID;

public interface CustomerFactory {
  Customer create(UUID userId, String name, String contact, String address);
}
