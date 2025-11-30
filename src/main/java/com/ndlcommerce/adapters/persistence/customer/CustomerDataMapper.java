package com.ndlcommerce.adapters.persistence.customer;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customer", schema = "ecommerce")
public class CustomerDataMapper {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserDataMapper user;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(length = 50)
  private String contact;

  @Column(columnDefinition = "TEXT")
  private String address;

  @Column(nullable = false)
  private boolean active = true;

  @Column(name = "created_by")
  private UUID createdBy;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_by")
  private UUID updatedBy;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public CustomerDataMapper(
      UserDataMapper user, String name, String contact, String address, UUID createdBy) {
    this.user = user;
    this.name = name;
    this.contact = contact;
    this.address = address;
    this.createdBy = createdBy;
  }

  public CustomerDataMapper(UserDataMapper user, String name, String contact, String address) {
    this.user = user;
    this.name = name;
    this.contact = contact;
    this.address = address;
  }
}
