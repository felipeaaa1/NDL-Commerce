package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserRequestModel;
import com.ndlcommerce.useCase.request.UserResponseModel;

public interface UserInputBoundary {
  UserResponseModel create(UserRequestModel requestModel);
}
