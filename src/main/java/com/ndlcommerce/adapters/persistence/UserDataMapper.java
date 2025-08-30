package com.ndlcommerce.adapters.persistence;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

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

    @Column(unique = true)
    private String email;

    @CreatedDate
    private LocalDateTime creationTime;

    public UserDataMapper(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
