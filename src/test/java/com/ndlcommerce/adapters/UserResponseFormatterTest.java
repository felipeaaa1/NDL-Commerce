package com.ndlcommerce.adapters;

import static org.assertj.core.api.Assertions.assertThat;

import com.ndlcommerce.adapters.presenter.UserResponseFormatter;
import com.ndlcommerce.useCase.request.user.UserResponseDTO;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserResponseFormatterTest {

  UserResponseFormatter userResponseFormatter;

  @BeforeEach
  void setup() {
    this.userResponseFormatter = new UserResponseFormatter();
  }

  @Test
  void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
    UserResponseDTO modelResponse =
        new UserResponseDTO(
            UUID.randomUUID(), "baeldung", "baeldung", "COMMON", "2020-12-20T03:00:00.000");
    UserResponseDTO formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

    assertThat(formattedResponse.getCreationTime()).isEqualTo("20/12/2020 03:00");
  }
}
