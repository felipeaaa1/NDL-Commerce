package com.ndlcommerce.useCase.interfaces.customer;

import com.ndlcommerce.adapters.persistence.customer.CustomerDataMapper;
import com.ndlcommerce.useCase.request.customer.CustomerDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRegisterDsGateway {

  boolean existsByName(String name);

  //  TODO: colocar constraingt para unique no user id do cutomer
  boolean existsByUser(UUID userId);

  List<CustomerDataMapper> list(CustomerDbRequestDTO requestDTO, Integer page, Integer size);

  CustomerDataMapper save(CustomerDbRequestDTO requestDTO);

  Optional<CustomerDataMapper> findById(UUID uuid);

    boolean existsByNameAndNotID(UUID id, String name);

    CustomerDataMapper update(UUID id, CustomerDbRequestDTO dbRequestDTO);
}
