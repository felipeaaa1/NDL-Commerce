package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserDsRequestModel;

public interface UserRegisterDsGateway {
  boolean existsByName(String name);

  void save(UserDsRequestModel requestModel);
}
