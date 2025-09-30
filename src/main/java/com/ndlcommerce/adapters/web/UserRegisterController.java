package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.UserInputBoundary;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
// @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserRegisterController {

  private final UserInputBoundary userInput;

  public UserRegisterController(UserInputBoundary userInput) {
    this.userInput = userInput;
  }

  @PostMapping
  public UserResponseDTO create(@RequestBody UserRequestDTO requestModel) {
    return userInput.create(requestModel);
  }

  @GetMapping
  public List<UserResponseDTO> listUsers(@RequestBody UserRequestDTO requestModel) {
    return userInput.list(requestModel);
  }
}
