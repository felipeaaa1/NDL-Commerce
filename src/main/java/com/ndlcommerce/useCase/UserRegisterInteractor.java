package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.entity.factory.UserFactory;
import com.ndlcommerce.entity.model.User;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.time.LocalDateTime;
import java.util.List;

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
  public UserResponseDTO create(UserRequestDTO requestModel) {
    if (userDsGateway.existsByName(requestModel.getLogin())) {
      return userPresenter.prepareFailView("existsByName");
    }
    User user =
        userFactory.create(
            requestModel.getLogin(),
            requestModel.getEmail(),
            requestModel.getType(),
            requestModel.getPassword());
    if (!user.passwordIsValid()) {
      return userPresenter.prepareFailView("passwordIsValid");
    }
    LocalDateTime now = LocalDateTime.now();
    UserDbRequestDTO userDsModel =
        new UserDbRequestDTO(
            user.getName(), user.getEmail(), user.getType(), user.getPassword(), now);

    userDsGateway.save(userDsModel);

    UserResponseDTO accountResponseModel =
        new UserResponseDTO(user.getName(), user.getEmail(), now.toString());
    return userPresenter.prepareSuccessView(accountResponseModel);
  }

  @Override
  public List<UserResponseDTO> list(UserRequestDTO requestModel) {
    User user =
        userFactory.create(
            requestModel.getLogin(),
            requestModel.getEmail(),
            requestModel.getType(),
            requestModel.getPassword());
    UserDbRequestDTO userDsModel =
        new UserDbRequestDTO(
            user.getName(),
            user.getEmail(),
            user.getType(),
            user.getPassword(),
            LocalDateTime.now());
    UserDataMapper first = userDsGateway.list(userDsModel).getFirst();

    UserResponseDTO accountResponseModel =
        new UserResponseDTO(first.getName(), first.getEmail(), first.getCreationTime().toString());

    //    return userPresenter.prepareSuccessView(accountResponseModel);
    return null;
  }
}
