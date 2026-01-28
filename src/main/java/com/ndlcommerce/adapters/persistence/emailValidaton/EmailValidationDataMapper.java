package com.ndlcommerce.adapters.persistence.emailValidaton;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "tb_email_validate", schema = "ecommerce")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmailValidationDataMapper {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @Column(name = "expires_at", nullable = false)
  private Instant expiresAt;

  @Column(name = "used_at")
  private Instant usedAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private UserDataMapper user;

  // JPA only
  protected EmailValidationDataMapper() {}

  public EmailValidationDataMapper(UserDataMapper user, Instant expiresAt) {
    this.user = user;
    this.expiresAt = expiresAt;
    this.createdAt = Instant.now();
  }

  public void markAsUsed() {
    this.usedAt = Instant.now();
  }

  public boolean isExpired() {
    return Instant.now().isAfter(this.expiresAt);
  }

  public boolean isUsed() {
    return this.usedAt != null;
  }

  public void setUsedAt(Instant usedAt) {
    this.usedAt = usedAt;
  }
}
