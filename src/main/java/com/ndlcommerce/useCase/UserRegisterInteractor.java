package com.ndlcommerce.useCase;

import com.ndlcommerce.entity.factory.UserFactory;
import com.ndlcommerce.entity.model.User;
import com.ndlcommerce.useCase.request.UserDsRequestModel;
import com.ndlcommerce.useCase.request.UserRequestModel;
import com.ndlcommerce.useCase.request.UserResponseModel;
import java.time.LocalDateTime;

public class UserRegisterInteractor implements UserInputBoundary {

  final UserRegisterDsGateway userDsGateway;
  final UserPresenter userPresenter;
  final UserFactory userFactory;

  public UserRegisterInteractor(
      UserRegisterDsGateway userDsGateway, UserPresenter userPresenter, UserFactory userFactory) {
    this.userDsGateway = userDsGateway;
    this.userPresenter = userPresenter;
    this.userFactory = userFactory;
  }

  @Override
  public UserResponseModel create(UserRequestModel requestModel) {
    if (userDsGateway.existsByName(requestModel.getLogin())) {
      return userPresenter.prepareFailView("User already exists.");
    }
    User user = userFactory.create(requestModel.getLogin(), requestModel.getPassword());
    if (!user.passwordIsValid()) {
      return userPresenter.prepareFailView("User password must have more than 5 characters.");
    }
    LocalDateTime now = LocalDateTime.now();
    UserDsRequestModel userDsModel =
        new UserDsRequestModel(user.getName(), user.getPassword(), now);

    userDsGateway.save(userDsModel);

    UserResponseModel accountResponseModel = new UserResponseModel(user.getName(), now.toString());
    return userPresenter.prepareSuccessView(accountResponseModel);
  }
}
