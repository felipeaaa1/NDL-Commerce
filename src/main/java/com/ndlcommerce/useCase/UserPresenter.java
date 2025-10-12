package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.util.List;

public interface UserPresenter {
  UserResponseDTO prepareSuccessView(UserResponseDTO user);

  UserResponseDTO prepareFailView(String error);

  List<UserResponseDTO> prepareListSuccessView(List<UserResponseDTO> list);
}
