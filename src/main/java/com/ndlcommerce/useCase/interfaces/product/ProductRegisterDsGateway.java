package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.adapters.persistence.product.ProductDataMapper;
import com.ndlcommerce.useCase.request.product.ProductDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRegisterDsGateway {

  boolean existsByName(String name);

  List<ProductDataMapper> list(ProductDbRequestDTO requestDTO, Integer page, Integer size);

  ProductDataMapper save(ProductDbRequestDTO requestDTO);

  Optional<ProductDataMapper> findById(UUID uuid);
}
