package com.ndlcommerce.adapters.persistence.emailValidaton;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.emailValidation.EmailValidationDsGateway;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JpaEmailValidation implements EmailValidationDsGateway {
  private final SecurityFilter securityFilter;
  private final JpaEmailRepository jpaEmailRepository;

  public JpaEmailValidation(SecurityFilter securityFilter, JpaEmailRepository jpaEmailRepository) {
    this.securityFilter = securityFilter;
    this.jpaEmailRepository = jpaEmailRepository;
  }

  @Override
  public UUID createValidationToken(UserDataMapper userId) {

    Instant expiresAt = Instant.now().plus(Duration.ofHours(24));

    EmailValidationDataMapper EmailValidationDataMapper =
        new EmailValidationDataMapper(userId, expiresAt);
    EmailValidationDataMapper save = jpaEmailRepository.save(EmailValidationDataMapper);

    return save.getId();
  }

  @Override
  public Optional<EmailValidationDataMapper> getById(UUID uuid) {
    return jpaEmailRepository.findById(uuid);
  }

  @Override
  public void markUsedToken(EmailValidationDataMapper emailValidationDataMapper) {
    emailValidationDataMapper.setUsedAt(Instant.now());
    jpaEmailRepository.save(emailValidationDataMapper);
  }

  @Override
  public void markUsedPrevipousTokens(UserDataMapper userDataMapper) {
    List<EmailValidationDataMapper> byuser =
        jpaEmailRepository.findByUserAndUsedAtIsNull(userDataMapper);

    if (!byuser.isEmpty()) {
      for (EmailValidationDataMapper validation : byuser) {
        validation.setUsedAt(Instant.now());
        jpaEmailRepository.save(validation);
      }
    }
  }
}
