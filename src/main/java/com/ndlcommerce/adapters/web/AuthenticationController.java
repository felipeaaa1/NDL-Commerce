package com.ndlcommerce.adapters.web;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.adapters.web.dto.AuthenticationDTO;
import com.ndlcommerce.adapters.web.dto.LoginResponseDTO;
import com.ndlcommerce.config.TokenService;
import com.ndlcommerce.entity.enums.UserType;
import com.ndlcommerce.useCase.interfaces.user.UserInputBoundary;
import com.ndlcommerce.useCase.request.user.UserRequestDTO;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final UserInputBoundary userInput;

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  public AuthenticationController(
      UserInputBoundary userInput,
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.userInput = userInput;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

    var userPass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(userPass);

    String token = tokenService.generateToken((UserDataMapper) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO requestModel) {
    requestModel.setType(UserType.COMMON);
    var userCreated = userInput.create(requestModel);
    return ResponseEntity.ok().body(userCreated);
  }

  @PostMapping("/emailValidation/{userID}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<?> sendEmailValidate(@PathVariable("userID") String userID)
      throws IOException {
    userInput.sendValidationToken(UUID.fromString(userID));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/verifyEmail")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<?> verifyEmail(@RequestParam(name = "token") String token) {
    userInput.verifyEmail(UUID.fromString(token));
    return ResponseEntity.ok().build();
  }
}
