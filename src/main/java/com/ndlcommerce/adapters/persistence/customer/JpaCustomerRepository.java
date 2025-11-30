package com.ndlcommerce.adapters.persistence.customer;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository
    extends JpaRepository<CustomerDataMapper, UUID>, CustomCustomerRepository {
  boolean existsByName(String name);

  boolean existsByNameAndIdNot(String name, UUID id);
}
