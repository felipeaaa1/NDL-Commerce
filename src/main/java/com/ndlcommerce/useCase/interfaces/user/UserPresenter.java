package com.ndlcommerce.useCase.interfaces.user;

import com.ndlcommerce.useCase.request.user.UserResponseDTO;
import java.util.List;

public interface UserPresenter {
  UserResponseDTO prepareSuccessView(UserResponseDTO user);

  UserResponseDTO prepareFailView(String error);

  List<UserResponseDTO> prepareListSuccessView(List<UserResponseDTO> list);
}
