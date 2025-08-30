package com.ndlcommerce.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserDataMapper, UUID> {
    Boolean existsByName(String name);
}
