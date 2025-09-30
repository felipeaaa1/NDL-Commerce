package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserDbRequestDTO;

public interface UserRegisterDsGateway {
  boolean existsByName(String name);

  void save(UserDbRequestDTO requestModel);
}
