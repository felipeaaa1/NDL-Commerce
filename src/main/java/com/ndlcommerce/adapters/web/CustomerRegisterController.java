package com.ndlcommerce.adapters.web;


import com.ndlcommerce.useCase.CustomerRegisterInteractor;
import com.ndlcommerce.useCase.interfaces.customer.CustomerInputBoundary;
import com.ndlcommerce.useCase.request.customer.CustomerRequestDTO;
import com.ndlcommerce.useCase.request.user.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping("/customer")
public class CustomerRegisterController {

    private final CustomerInputBoundary customerInput;

    public CustomerRegisterController(CustomerInputBoundary customerInput) {
        this.customerInput = customerInput;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CustomerRequestDTO requestModel) {
        var userCreated = customerInput.create(requestModel);
        return ResponseEntity.ok().body(userCreated);
    }
}
