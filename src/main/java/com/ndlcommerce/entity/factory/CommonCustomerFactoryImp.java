package com.ndlcommerce.entity.factory;

import com.ndlcommerce.entity.model.CommonCustomer;
import com.ndlcommerce.entity.model.Customer;
import java.util.UUID;

public class CommonCustomerFactoryImp implements CustomerFactory {

  @Override
  public Customer create(UUID userId, String name, String contact, String address) {
    return new CommonCustomer(userId, name, contact, address);
  }
}
