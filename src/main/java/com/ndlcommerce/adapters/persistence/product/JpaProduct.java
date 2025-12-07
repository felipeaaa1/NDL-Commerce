package com.ndlcommerce.adapters.persistence.product;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.product.ProductRegisterDsGateway;
import com.ndlcommerce.useCase.request.product.ProductDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JpaProduct implements ProductRegisterDsGateway {

  private final JpaProductRepository repository;
  private final SecurityFilter securityFilter;

  public JpaProduct(JpaProductRepository repository, SecurityFilter securityFilter) {
    this.repository = repository;
    this.securityFilter = securityFilter;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }

  @Override
  public List<ProductDataMapper> list(ProductDbRequestDTO requestDTO, Integer page, Integer size) {

    return null;
  }

  @Override
  public ProductDataMapper save(ProductDbRequestDTO requestDTO) {

    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    ProductDataMapper entity =
        new ProductDataMapper(
            requestDTO.getName(), requestDTO.getDescription(), userLogado.getId());

    entity.setUpdatedBy(userLogado.getId());

    return repository.save(entity);
  }

  @Override
  public Optional<ProductDataMapper> findById(UUID uuid) {
    return repository.findById(uuid);
  }
}
