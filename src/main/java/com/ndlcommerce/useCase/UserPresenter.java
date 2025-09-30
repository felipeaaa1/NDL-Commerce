package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserResponseDTO;

public interface UserPresenter {
  UserResponseDTO prepareSuccessView(UserResponseDTO user);

  UserResponseDTO prepareFailView(String error);
}
