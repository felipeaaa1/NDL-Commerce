package com.ndlcommerce.adapters.persistence.customer;

import java.util.List;

public interface CustomCustomerRepository {
  List<CustomerDataMapper> search(
      String login, String name, String contact, Boolean active, Integer page, Integer size);
}
