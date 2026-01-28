package com.ndlcommerce.useCase.interfaces.user;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.useCase.request.user.UserDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRegisterDsGateway {
  boolean existsByLogin(String login);

  boolean existsByEmail(String email);

  boolean existsByLoginAndNotId(String login, UUID id);

  UserDataMapper save(UserDbRequestDTO requestDTO);

  List<UserDataMapper> list(UserDbRequestDTO requestDTO);

  Optional<UserDataMapper> getById(UUID userId);

  UserDataMapper update(UUID uuid, UserDbRequestDTO userDsModel);

  boolean existsByEmailAndNotId(String email, UUID userId);

  void delete(UserDataMapper userDataMapper);

  void validateEmail(UUID uuid);
}
