package com.ndlcommerce.useCase.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserDsRequestModel {

    private final String login;
    private final String password;
    private final LocalDateTime creationTime;

    public UserDsRequestModel(String login, String password, LocalDateTime creationTime) {
        this.login = login;
        this.password = password;
        this.creationTime = creationTime;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDsRequestModel)) return false;
        UserDsRequestModel that = (UserDsRequestModel) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, creationTime);
    }
}
