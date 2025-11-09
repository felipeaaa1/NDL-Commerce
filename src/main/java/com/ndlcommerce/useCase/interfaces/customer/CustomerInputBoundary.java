package com.ndlcommerce.useCase.interfaces.customer;

import com.ndlcommerce.useCase.request.customer.CustomerFilterDTO;
import com.ndlcommerce.useCase.request.customer.CustomerRequestDTO;
import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerInputBoundary {
    CustomerResponseDTO create(CustomerRequestDTO requestModel);

    List<?> list(CustomerFilterDTO requestModel, int page, int size);

    CustomerResponseDTO getById(UUID userId);

    CustomerResponseDTO updateCustomer(UUID customerId, CustomerRequestDTO requestModel);

    CustomerResponseDTO deleteCustomer(UUID uuid);
}
