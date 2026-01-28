package com.ndlcommerce.adapters.persistence.emailValidaton;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailRepository extends JpaRepository<EmailValidationDataMapper, UUID> {
  List<EmailValidationDataMapper> findByUserAndUsedAtIsNull(UserDataMapper user);
}
