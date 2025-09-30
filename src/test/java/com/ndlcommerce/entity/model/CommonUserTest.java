package com.ndlcommerce.entity.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.ndlcommerce.entity.UserType;
import org.junit.jupiter.api.Test;

class CommonUserTest {

  @Test
  void givenShortPassword_whenPasswordIsNotValid_thenIsFalse() {
    User user = new CommonUser("X", "dscdc", UserType.COMMON, "123");
    assertThat(user.passwordIsValid()).isFalse();
  }

  @Test
  void givenValidUser_whenPasswordAndNameAreValid_thenIsTrue() {
    User user = new CommonUser("user1", "user1", UserType.COMMON, "Aabcdef");
    assertThat(user.passwordIsValid()).isTrue();
    assertThat(user.nameIsValid()).isTrue();
  }

  @Test
  void givenLowerPassword_whenPasswordIsNotValid_thenIsFalse() {
    User user = new CommonUser("X", "X", UserType.COMMON, "asasdas");
    assertThat(user.passwordIsValid()).isFalse();
  }

  @Test
  void givenUpperPassword_whenPasswordIsNotValid_thenIsFalse() {
    User user = new CommonUser("X", "X", UserType.COMMON, "ASDASDSAV");
    assertThat(user.passwordIsValid()).isFalse();
  }

  @Test
  void givenShortUser_whenUserIsNotValid_thenIsFalse() {
    User user = new CommonUser("X", "X", UserType.COMMON, "Aa12345");
    assertThat(user.nameIsValid()).isFalse();
  }
}
