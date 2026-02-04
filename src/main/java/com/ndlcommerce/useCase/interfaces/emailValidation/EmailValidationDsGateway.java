package com.ndlcommerce.useCase.interfaces.emailValidation;

import com.ndlcommerce.adapters.persistence.emailValidaton.EmailValidationDataMapper;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import java.util.Optional;
import java.util.UUID;

public interface EmailValidationDsGateway {
  UUID createValidationToken(UserDataMapper userId);

  Optional<EmailValidationDataMapper> getById(UUID uuid);

  void markUsedToken(EmailValidationDataMapper emailValidationDataMapper);

  void markUsedPrevipousTokens(UserDataMapper userDataMapper);
}
