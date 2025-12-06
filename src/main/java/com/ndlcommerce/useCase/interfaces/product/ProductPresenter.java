package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import java.util.List;

public interface ProductPresenter {

  ProductResponseDTO prepareSuccessView(ProductResponseDTO product);

  ProductResponseDTO prepareFailView(String error);

  List<ProductResponseDTO> prepareListSuccessView(List<ProductResponseDTO> list);
}
