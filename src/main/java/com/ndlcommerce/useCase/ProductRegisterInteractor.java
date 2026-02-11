package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.product.ProductDataMapper;
import com.ndlcommerce.entity.factory.interfaces.ProductFactory;
import com.ndlcommerce.entity.model.interfaces.Product;
import com.ndlcommerce.useCase.interfaces.product.ProductInputBoundary;
import com.ndlcommerce.useCase.interfaces.product.ProductPresenter;
import com.ndlcommerce.useCase.interfaces.product.ProductRegisterDsGateway;
import com.ndlcommerce.useCase.request.product.ProductDbRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductFilterDTO;
import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import java.util.List;
import java.util.UUID;

public class ProductRegisterInteractor implements ProductInputBoundary {

  private final ProductRegisterDsGateway productDsGateway;
  private final ProductPresenter productPresenter;
  private final ProductFactory productFactory;

  public ProductRegisterInteractor(
      ProductRegisterDsGateway productDsGateway,
      ProductPresenter productPresenter,
      ProductFactory productFactory) {
    this.productDsGateway = productDsGateway;
    this.productPresenter = productPresenter;
    this.productFactory = productFactory;
  }

  @Override
  public ProductResponseDTO create(ProductRequestDTO requestDTO) {

    Product product = productFactory.create(requestDTO.getName(), requestDTO.getDescription());

    if (!product.nameIsValid()) {
      return productPresenter.prepareFailView("NameNotValid");
    } else if (!product.descriptionIsValid()) {
      return productPresenter.prepareFailView("DescriptionNotValid");
    } else if (productDsGateway.existsByName(product.getName())) {
      return productPresenter.prepareFailView("ExistByName");
    }

    ProductDbRequestDTO dbRequest =
        new ProductDbRequestDTO(product.getName(), product.getDescription());

    ProductDataMapper save = productDsGateway.save(dbRequest);

    ProductResponseDTO response =
        new ProductResponseDTO(
            save.getId(), save.getName(), save.getDescription(), save.getCreatedAt().toString());

    return productPresenter.prepareSuccessView(response);
  }

  @Override
  public List<?> list(ProductFilterDTO filter, int page, int size) {
    return List.of();
  }

  @Override
  public ProductResponseDTO getById(UUID productId) {
    return null;
  }

  @Override
  public ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO requestDTO) {
    return null;
  }

  @Override
  public ProductResponseDTO deleteProduct(UUID productId) {
    return null;
  }
}
