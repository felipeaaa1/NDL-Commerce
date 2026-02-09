package com.ndlcommerce.adapters.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthenticationDTO(
    @Schema(example = "common user") String login, @Schema(example = "Password") String password) {}
