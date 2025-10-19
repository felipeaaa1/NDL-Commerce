package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserFilterDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.util.List;
import java.util.UUID;

public interface UserInputBoundary {
  UserResponseDTO create(UserRequestDTO requestModel);

  List<?> list(UserFilterDTO requestModel, int page, int size);

  UserResponseDTO getById(UUID userId);
}
