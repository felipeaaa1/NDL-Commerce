package com.ndlcommerce.useCase.interfaces.customer;

import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;
import java.util.List;

public interface CustomerPresenter {
  CustomerResponseDTO prepareSuccessView(CustomerResponseDTO Customer);

  CustomerResponseDTO prepareFailView(String error);

  List<CustomerResponseDTO> prepareListSuccessView(List<CustomerResponseDTO> list);
}
