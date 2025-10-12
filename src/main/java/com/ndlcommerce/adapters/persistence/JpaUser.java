package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.useCase.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
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
  public void save(UserDbRequestDTO user) {

    //    TODO: necessário validar a senha na volta.
    UserDataMapper entity =
        new UserDataMapper(
            user.getLogin(), user.getEmail(), user.getType(), encoder.encode(user.getPassword()));
    repository.save(entity);
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
}
