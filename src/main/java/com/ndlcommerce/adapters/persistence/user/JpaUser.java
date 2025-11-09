package com.ndlcommerce.adapters.persistence.user;

import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.user.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.user.UserDbRequestDTO;
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
  private final SecurityFilter securityFilter;

  public JpaUser(
      JpaUserRepository repository, PasswordEncoder encoder, SecurityFilter securityFilter) {
    this.repository = repository;
    this.encoder = encoder;
    this.securityFilter = securityFilter;
  }

  @Override
  public boolean existsByLogin(String login) {
    return repository.existsByLogin(login);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public boolean existsByLoginAndNotId(String login, UUID id) {
    return repository.existsByLoginAndIdNot(login, id);
  }

  @Override
  public UserDataMapper save(UserDbRequestDTO user) {

    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();
    UserDataMapper entity =
        new UserDataMapper(
            user.getLogin(),
            user.getEmail(),
            user.getType(),
            encoder.encode(user.getPassword()),
            userLogado.getId());
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
            .withIgnorePaths(
                "id",
                "password",
                "creationTime",
                "updateTime",
                "enabled",
                "accountNonLocked",
                "accountNonExpired",
                "credentialsNonExpired")
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

    if (repository.findById(uuid).isEmpty()) {
      return null;
    }
    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    UserDataMapper user = byId.get();
    user.setLogin(userDsModel.getLogin() == null ? user.getLogin() : userDsModel.getLogin());
    user.setType(
        userDsModel.getType() == null || userDsModel.getType().toString().isEmpty()
            ? user.getType()
            : userDsModel.getType());
    user.setEmail(userDsModel.getEmail() == null ? user.getEmail() : userDsModel.getEmail());
    user.setUpdated_by(userLogado.getId());
    return repository.save(user);
  }

  @Override
  public boolean existsByEmailAndNotId(String email, UUID userId) {
    return repository.existsByEmailAndIdNot(email, userId);
  }

  @Override
  public void delete(UserDataMapper userDataMapper) {
    repository.delete(userDataMapper);
  }
}
