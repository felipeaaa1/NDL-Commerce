package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import java.util.List;
import java.util.UUID;

public interface UserRegisterDsGateway {
  boolean existsByName(String name);

  void save(UserDbRequestDTO requestDTO);

  List<UserDataMapper> list(UserDbRequestDTO requestDTO);

  UserDataMapper getById(UUID userId);
}
