package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.UserInputBoundary;
import com.ndlcommerce.useCase.request.UserFilterDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO requestModel) {
    var userCreated = userInput.create(requestModel);
    return ResponseEntity.ok().body(userCreated);
  }

  @GetMapping
  public ResponseEntity<?> listUsers(
      UserFilterDTO filter,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    var result = userInput.list(filter, page, size);
    return ResponseEntity.ok().body(result);
  }
}
