package com.ndlcommerce.entity.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommonUserTest {

    @Test
    void givenShortPassword_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("X", "123");
        assertThat(user.passwordIsValid()).isFalse();
    }

    @Test
    void givenValidUser_whenPasswordAndNameAreValid_thenIsTrue() {
        User user = new CommonUser("user1", "Aabcdef");
        assertThat(user.passwordIsValid()).isTrue();
        assertThat(user.nameIsValid()).isTrue();
    }

    @Test
    void givenLowerPassword_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("X", "asasdas");
        assertThat(user.passwordIsValid()).isFalse();
    }


    @Test
    void givenUpperPassword_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("X", "ASDASDSAV");
        assertThat(user.passwordIsValid()).isFalse();
    }

    @Test
    void givenShortUser_whenUserIsNotValid_thenIsFalse() {
        User user = new CommonUser("X", "Aa12345");
        assertThat(user.nameIsValid()).isFalse();
    }
}
