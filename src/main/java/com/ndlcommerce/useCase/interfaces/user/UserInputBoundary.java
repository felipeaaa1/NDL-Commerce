package com.ndlcommerce.useCase.interfaces.user;

import com.ndlcommerce.useCase.request.user.UpdateUserDTO;
import com.ndlcommerce.useCase.request.user.UserFilterDTO;
import com.ndlcommerce.useCase.request.user.UserRequestDTO;
import com.ndlcommerce.useCase.request.user.UserResponseDTO;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserInputBoundary {
  UserResponseDTO create(UserRequestDTO requestModel);

  List<?> list(UserFilterDTO requestModel, int page, int size);

  UserResponseDTO getById(UUID userId);

  UserResponseDTO updateUser(UUID userId, UpdateUserDTO requestModel);

  UserResponseDTO deleteUser(UUID uuid);

  UserResponseDTO sendValidationToken(UUID userID) throws IOException;

  UserResponseDTO verifyEmail(UUID uuid);
}
