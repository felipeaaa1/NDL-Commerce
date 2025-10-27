package com.ndlcommerce.adapters.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface JpaUserRepository extends JpaRepository<UserDataMapper, UUID> {

  UserDetails findByName(String name);

  Boolean existsByName(String name);

  Boolean existsByEmail(String name);

  boolean existsByNameAndIdNot(String name, UUID id);

  boolean existsByEmailAndIdNot(String email, UUID userId);
}
