package com.ndlcommerce.adapters.persistence.customer;

import com.ndlcommerce.adapters.persistence.user.JpaUserRepository;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.exception.BusinessException;
import com.ndlcommerce.useCase.interfaces.customer.CustomerRegisterDsGateway;
import com.ndlcommerce.useCase.interfaces.user.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.customer.CustomerDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JpaCustomer implements CustomerRegisterDsGateway {

  private final JpaCustomerRepository repository;
  private final JpaUserRepository userRepository;
  private final SecurityFilter securityFilter;
  private final UserRegisterDsGateway userDsGateway;

  public JpaCustomer(
      JpaCustomerRepository repository,
      JpaUserRepository userRepository,
      SecurityFilter securityFilter,
      UserRegisterDsGateway userDsGateway) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.securityFilter = securityFilter;
    this.userDsGateway = userDsGateway;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }

  @Override
  public boolean existsByUser(UUID userId) {
    return userRepository.findById(userId).isPresent();
  }

  @Override
  public List<CustomerDataMapper> list(
      CustomerDbRequestDTO requestDTO, Integer page, Integer size) {

    return repository.search(
        requestDTO.getUserLogin(),
        requestDTO.getName(),
        requestDTO.getContact(),
        requestDTO.isActive(),
        page,
        size);
  }

  @Override
  public CustomerDataMapper save(CustomerDbRequestDTO requestDTO) {
    UserDataMapper user =
        userRepository
            .findById(requestDTO.getUserId())
            .orElseThrow(
                () ->
                    new BusinessException(
                        "não era para essa excessão ter chegado aqui. coloca no interactor."));
    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    CustomerDataMapper entity =
        new CustomerDataMapper(
            user,
            requestDTO.getName(),
            requestDTO.getContact(),
            requestDTO.getAddress(),
            userLogado != null ? userLogado.getId() : null);

    return repository.save(entity);
  }

  @Override
  public Optional<CustomerDataMapper> findById(UUID uuid) {
    return repository.findById(uuid);
  }
}
