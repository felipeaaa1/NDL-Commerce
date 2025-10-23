package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRegisterDsGateway {
  boolean existsByName(String name);

  boolean existsByNameAndNotId(String name, UUID id);

  UserDataMapper save(UserDbRequestDTO requestDTO);

  List<UserDataMapper> list(UserDbRequestDTO requestDTO);

  Optional<UserDataMapper> getById(UUID userId);

  UserDataMapper update(UUID uuid, UserDbRequestDTO userDsModel);
}
