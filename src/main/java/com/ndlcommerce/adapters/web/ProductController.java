package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.interfaces.product.ProductInputBoundary;
import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping("/product")
public class ProductController {

  private final ProductInputBoundary productInputBoundary;

  public ProductController(ProductInputBoundary productInputBoundary) {
    this.productInputBoundary = productInputBoundary;
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody ProductRequestDTO requestModel) {
    var productCreated = productInputBoundary.create(requestModel);
    return ResponseEntity.ok().body(productCreated);
  }
}
