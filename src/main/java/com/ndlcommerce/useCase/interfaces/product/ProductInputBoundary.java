package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.useCase.request.product.ProductFilterDTO;
import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import java.util.List;
import java.util.UUID;

public interface ProductInputBoundary {

  ProductResponseDTO create(ProductRequestDTO requestDTO);

  List<?> list(ProductFilterDTO filter, int page, int size);

  ProductResponseDTO getById(UUID productId);

  ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO requestDTO);

  ProductResponseDTO deleteProduct(UUID productId);
}
