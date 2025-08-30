package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.UserInputBoundary;
import com.ndlcommerce.useCase.request.UserRequestModel;
import com.ndlcommerce.useCase.request.UserResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRegisterController {

    private final UserInputBoundary userInput;

    public UserRegisterController(UserInputBoundary userInput) {
        this.userInput = userInput;
    }

    @PostMapping
    public UserResponseModel create(@RequestBody UserRequestModel requestModel) {
        return userInput.create(requestModel);
    }
}
