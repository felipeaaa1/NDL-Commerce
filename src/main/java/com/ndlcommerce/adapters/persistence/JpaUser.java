package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.useCase.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JpaUser implements UserRegisterDsGateway {

  private final JpaUserRepository repository;
  private final PasswordEncoder encoder;

  public JpaUser(JpaUserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }

  @Override
  public boolean existsByNameAndNotId(String name, UUID id) {
    return repository.existsByNameAndIdNot(name, id);
  }

  @Override
  public UserDataMapper save(UserDbRequestDTO user) {

    //    TODO: necessário validar a senha na volta.
    UserDataMapper entity =
        new UserDataMapper(
            user.getLogin(), user.getEmail(), user.getType(), encoder.encode(user.getPassword()));
    return repository.save(entity);
  }

  @Override
  public List<UserDataMapper> list(UserDbRequestDTO requestDTO) {
    UserDataMapper probe =
        new UserDataMapper(requestDTO.getLogin(), requestDTO.getEmail(), requestDTO.getType());

    ExampleMatcher matcher =
        ExampleMatcher.matching()
            .withIgnoreCase()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<UserDataMapper> example = Example.of(probe, matcher);

    return repository.findAll(example);
  }

  @Override
  public Optional<UserDataMapper> getById(UUID userId) {
    return repository.findById(userId);
  }

  @Override
  public UserDataMapper update(UUID uuid, UserDbRequestDTO userDsModel) {
    Optional<UserDataMapper> byId = repository.findById(uuid);

    if (repository.findById(uuid).isEmpty()){
      return null;
    }
    UserDataMapper user = byId.get();
    user.setName(userDsModel.getLogin() == null ? user.getName() : userDsModel.getLogin());
    user.setType(userDsModel.getType().toString().isEmpty() ? user.getType(): userDsModel.getType());
    user.setEmail(userDsModel.getEmail() == null ? user.getEmail(): userDsModel.getEmail());
    return repository.save(user);
  }
}
