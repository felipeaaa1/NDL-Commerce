package com.ndlcommerce.adapters.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserDataMapper, UUID> {
  Boolean existsByName(String name);
}
