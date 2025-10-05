package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.useCase.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JpaUser implements UserRegisterDsGateway {

  private final JpaUserRepository repository;

  public JpaUser(JpaUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }

  @Override
  public void save(UserDbRequestDTO user) {
    UserDataMapper entity =
        new UserDataMapper(user.getLogin(), user.getEmail(), user.getType(), user.getPassword());
    repository.save(entity);
  }

  @Override
  public List<UserDataMapper> list(UserDbRequestDTO requestDTO) {
    return repository.findAll();
  }
}
