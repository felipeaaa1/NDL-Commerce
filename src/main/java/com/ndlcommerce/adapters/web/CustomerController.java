package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.interfaces.customer.CustomerInputBoundary;
import com.ndlcommerce.useCase.request.customer.CustomerFilterDTO;
import com.ndlcommerce.useCase.request.customer.CustomerRequestDTO;
import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerInputBoundary customerInput;

  public CustomerController(CustomerInputBoundary customerInput) {
    this.customerInput = customerInput;
  }

  @GetMapping
  public ResponseEntity<?> listCustomers(
      @RequestBody CustomerFilterDTO filter,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "103") int size) {

    var result = customerInput.list(filter, page, size);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getCustomerById(@PathVariable("id") String id) {
    var result = customerInput.getById(UUID.fromString(id));
    return ResponseEntity.ok().body(result);
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody CustomerRequestDTO requestModel) {
    var userCreated = customerInput.create(requestModel);
    return ResponseEntity.ok().body(userCreated);
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> updateCustomer(
      @PathVariable("id") String id, @RequestBody CustomerRequestDTO requestModel) {

    var response = customerInput.updateCustomer(UUID.fromString(id), requestModel);

    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
    CustomerResponseDTO responseDTO = customerInput.deleteCustomer(UUID.fromString(id));
    return ResponseEntity.ok().body(responseDTO);
  }
}
