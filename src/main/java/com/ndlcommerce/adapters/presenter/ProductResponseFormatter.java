package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.exception.BusinessException;
import com.ndlcommerce.exception.EntityAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.product.ProductPresenter;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ProductResponseFormatter implements ProductPresenter {

  private static final Map<String, RuntimeException> ERRORS =
      Map.of(
          "NameNotValid",
          new BusinessException(
              "Nome do produto não é válido, Nome deve ter no mínimo 3 e no max 200 letras"),
          "DescriptionNotValid",
          new BusinessException(
              "o Descrição não é válida, Descrição deve ter no mínimo 5 e no max 200 letras"),
          "ExistByName",
          new EntityAlreadyExistsException("Produto Já cadastrado"));

  @Override
  public ProductResponseDTO prepareSuccessView(ProductResponseDTO product) {
    LocalDateTime date = LocalDateTime.parse(product.getCreatedAt());
    product.setCreatedAt(date.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    return product;
  }

  @Override
  public ProductResponseDTO prepareFailView(String error) {
    RuntimeException runtimeException = ERRORS.get(error);
    if (runtimeException != null) {
      throw runtimeException;
    }

    throw new RuntimeException("Erro desconhecido: " + error);
  }

  @Override
  public List<ProductResponseDTO> prepareListSuccessView(List<ProductResponseDTO> list) {
    return list;
  }
}
