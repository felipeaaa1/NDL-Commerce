package com.ndlcommerce.adapters.persistence.product;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductDataMapper, UUID> {

  boolean existsByName(String name);

  boolean existsByNameAndIdNot(String name, UUID id);
}
