package com.ndlcommerce.useCase.request.customer;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

public class CustomerDbRequestDTO {

    @Getter
    private String name;
    @Getter
    private String contact;
    @Getter
    private String address;
    @Getter
    @Setter
    private boolean active;
    @Getter
    private UUID userId;

    public CustomerDbRequestDTO(String name, String contact, String address, boolean active, UUID userId) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.active = active;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDbRequestDTO that = (CustomerDbRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(contact, that.contact) && Objects.equals(address, that.address) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact, address, active, userId);
    }
}