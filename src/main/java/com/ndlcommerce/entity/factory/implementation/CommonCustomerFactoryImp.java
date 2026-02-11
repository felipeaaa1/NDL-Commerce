package com.ndlcommerce.entity.factory.implementation;

import com.ndlcommerce.entity.factory.interfaces.CustomerFactory;
import com.ndlcommerce.entity.model.implementation.CommonCustomer;
import com.ndlcommerce.entity.model.interfaces.Customer;
import java.util.UUID;

public class CommonCustomerFactoryImp implements CustomerFactory {

  @Override
  public Customer create(UUID userId, String name, String contact, String address) {
    return new CommonCustomer(userId, name, contact, address);
  }
}
