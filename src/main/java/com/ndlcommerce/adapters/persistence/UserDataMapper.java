package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.entity.UserType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "app_user", schema = "ecommerce")
public class UserDataMapper implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  private String name;

  private String password;

  @Column(name = "user_type")
  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private UserType type;

  @Column(unique = true)
  private String email;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime creationTime;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updateTime;

  @Column(nullable = false)
  private boolean enabled = true;

  @Column(name = "account_non_locked", nullable = false)
  private boolean accountNonLocked = false;

  @Column(name = "account_non_expired", nullable = false)
  private boolean accountNonExpired = true;

  @Column(name = "credentials_non_expired", nullable = false)
  private boolean credentialsNonExpired = true;

  public UserDataMapper(String name, String email, UserType type, String password) {
    this.name = name;
    this.email = email;
    this.type = type;
    this.password = password;
  }

  public UserDataMapper(String name, String email, UserType type) {
    this.name = name;
    this.email = email;
    this.type = type;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + type.name()));
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
