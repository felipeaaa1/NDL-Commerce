package com.ndlcommerce.useCase.interfaces.customer;

import com.ndlcommerce.adapters.persistence.customer.CustomerDataMapper;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.useCase.request.customer.CustomerDbRequestDTO;
import com.ndlcommerce.useCase.request.user.UserDbRequestDTO;

import java.util.UUID;

public interface CustomerRegisterDsGateway {

    boolean existsByName(String name);

//  TODO: colocar constraingt para unique no user id do cutomer
    boolean existsByUser(UUID userId);

    CustomerDataMapper save(CustomerDbRequestDTO requestDTO);


}
