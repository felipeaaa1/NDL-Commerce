package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.UserInputBoundary;
import com.ndlcommerce.useCase.request.UserFilterDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserRegisterController {

  private final UserInputBoundary userInput;

  public UserRegisterController(UserInputBoundary userInput) {
    this.userInput = userInput;
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO requestModel) {
    var userCreated = userInput.create(requestModel);
    return ResponseEntity.ok().body(userCreated);
  }

  @GetMapping
  public ResponseEntity<?> listUsers(
      @RequestBody UserFilterDTO filter,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    var result = userInput.list(filter, page, size);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
    var result = userInput.getById(UUID.fromString(id));
    return ResponseEntity.ok().body(result);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> updateUser(
      @PathVariable("id") String id, @RequestBody UserRequestDTO requestModel) {
    var result = userInput.updateUser(UUID.fromString(id), requestModel);
    return ResponseEntity.ok().body(result);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
    UserResponseDTO userResponseDTO = userInput.deleteUser(UUID.fromString(id));
    return ResponseEntity.ok().body(userResponseDTO);
  }
}
