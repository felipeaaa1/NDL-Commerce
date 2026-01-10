package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.exception.BusinessException;
import com.ndlcommerce.useCase.interfaces.customer.CustomerPresenter;
import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class CustomerResponseFormatter implements CustomerPresenter {

  private static final Map<String, RuntimeException> ERRORS =
      Map.of(
          "UserNotExistsOrInactive",
              new BusinessException("Usuário não encontrado ou esta inativo"),
              "NameNotValid", new BusinessException("Nome do Cliente não é válido. Nome deve ter entre 4 e 50 caracteres."),
              "existsByName", new BusinessException("Nome de Cliente já registrado."));

  @Override
  public CustomerResponseDTO prepareSuccessView(CustomerResponseDTO customer) {
    if (customer != null) {
      LocalDateTime responseTime = LocalDateTime.parse(customer.getCreationTime());
      customer.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
      return customer;
    } else {
      return null;
    }
  }

  @Override
  public CustomerResponseDTO prepareFailView(String error) {

    RuntimeException exception = ERRORS.get(error);
    if (exception != null) throw exception;

    throw new RuntimeException("Erro desconhecido: " + error);
  }

  @Override
  public List<CustomerResponseDTO> prepareListSuccessView(List<CustomerResponseDTO> list) {
    return list;
  }
}
