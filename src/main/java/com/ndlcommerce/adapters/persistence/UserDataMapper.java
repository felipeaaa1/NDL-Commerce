package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.entity.UserType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "app_user")
@Data
public class UserDataMapper {

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

  // this is needed by JPA to instantiate objects, using proxy mechanisms, and performing seamless
  // mapping between entities and database tables
  public UserDataMapper() {}
}
