package com.ndlcommerce.useCase;

import com.ndlcommerce.useCase.request.UserResponseModel;

public interface UserPresenter {
    UserResponseModel prepareSuccessView(UserResponseModel user);

    UserResponseModel prepareFailView(String error);
}
