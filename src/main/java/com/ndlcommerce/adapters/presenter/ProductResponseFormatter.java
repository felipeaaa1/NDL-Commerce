package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.exception.EntityAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.product.ProductPresenter;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductResponseFormatter implements ProductPresenter {

  @Override
  public ProductResponseDTO prepareSuccessView(ProductResponseDTO product) {
    LocalDateTime date = LocalDateTime.parse(product.getCreatedAt());
    product.setCreatedAt(date.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    return product;
  }

  @Override
  public ProductResponseDTO prepareFailView(String error) {
    if ("NameNotValid".equals(error)) {
      throw new BusinessException(
          "Nome do produto não é válido, Nome deve ter no mínimo 3 e no max 200 letras");
    } else if ("DescriptionNotValid".equals(error)) {
      throw new BusinessException(
          "o Descrição não é válida, Descrição deve ter no mínimo 5 e no max 200 letras");
    } else if ("ExistByName".equals(error)) {
      throw new EntityAlreadyExistsException("Produto Já cadastrado");
    } else {
      throw new RuntimeException("Erro desconhecido");
    }
  }

  @Override
  public List<ProductResponseDTO> prepareListSuccessView(List<ProductResponseDTO> list) {
    return list;
  }
}
