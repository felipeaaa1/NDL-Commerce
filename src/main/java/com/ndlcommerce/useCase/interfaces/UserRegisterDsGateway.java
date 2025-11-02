package com.ndlcommerce.useCase.interfaces;

import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRegisterDsGateway {
  boolean existsByLogin(String name);

  boolean existsByEmail(String email);

  boolean existsByLoginAndNotId(String name, UUID id);

  UserDataMapper save(UserDbRequestDTO requestDTO);

  List<UserDataMapper> list(UserDbRequestDTO requestDTO);

  Optional<UserDataMapper> getById(UUID userId);

  UserDataMapper update(UUID uuid, UserDbRequestDTO userDsModel);

  boolean existsByEmailAndNotId(String email, UUID userId);

  void delete(UserDataMapper userDataMapper);
}
