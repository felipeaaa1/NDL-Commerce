package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.entity.factory.UserFactory;
import com.ndlcommerce.entity.model.User;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import com.ndlcommerce.useCase.request.UserFilterDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    if (userDsGateway.existsByLogin(requestModel.getLogin())) {
      return userPresenter.prepareFailView("existsByName");
    }
    if (userDsGateway.existsByEmail(requestModel.getEmail())) {
      return userPresenter.prepareFailView("existsByEmail");
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
    if (!user.loginIsValid()) {
      return userPresenter.prepareFailView("nameIsValid");
    }
    UserDbRequestDTO userDsModel =
        new UserDbRequestDTO(user.getLogin(), user.getEmail(), user.getType(), user.getPassword());

    UserDataMapper save = userDsGateway.save(userDsModel);

    UserResponseDTO accountResponseModel =
        new UserResponseDTO(
            save.getId(),
            save.getLogin(),
            save.getEmail(),
            save.getType().toString(),
            save.getCreationTime().toString());
    return userPresenter.prepareSuccessView(accountResponseModel);
  }

  @Override
  public List<UserResponseDTO> list(UserFilterDTO userFilterDTO, int page, int size) {

    UserDbRequestDTO userDbRequestDTO =
        new UserDbRequestDTO(
            userFilterDTO.getLogin(), userFilterDTO.getEmail(), userFilterDTO.getType());

    List<UserDataMapper> list = userDsGateway.list(userDbRequestDTO);
    List<UserResponseDTO> responseList =
        list.stream()
            .map(
                user ->
                    new UserResponseDTO(
                        user.getId(),
                        user.getLogin(),
                        user.getEmail(),
                        user.getType().toString(),
                        user.getCreationTime().toString()))
            .toList();

    return userPresenter.prepareListSuccessView(responseList);
  }

  @Override
  public UserResponseDTO getById(UUID userId) {

    Optional<UserDataMapper> opt = userDsGateway.getById(userId);

    if (opt.isEmpty()) {
      return userPresenter.prepareFailView("NotFound");
    }
    UserDataMapper user = opt.get();
    UserResponseDTO accountResponseModel =
        new UserResponseDTO(
            user.getId(),
            user.getLogin(),
            user.getEmail(),
            user.getType().toString(),
            user.getCreationTime().toString());
    return userPresenter.prepareSuccessView(accountResponseModel);
  }

  //  TODO: fix updated_at and created_at columns
  @Override
  public UserResponseDTO updateUser(UUID userId, UserRequestDTO requestModel) {
    if (userDsGateway.existsByLoginAndNotId(requestModel.getLogin(), userId)) {
      return userPresenter.prepareFailView("existsByName");
    }
    if (userDsGateway.existsByEmailAndNotId(requestModel.getEmail(), userId)) {
      return userPresenter.prepareFailView("existsByEmail");
    }
    User user =
        userFactory.create(
            requestModel.getLogin(),
            requestModel.getEmail(),
            requestModel.getType(),
            requestModel.getPassword());

    if (!user.loginIsValid()) {
      return userPresenter.prepareFailView("nameIsValid");
    }

    UserDbRequestDTO userDsModel =
        new UserDbRequestDTO(user.getLogin(), user.getEmail(), user.getType());

    UserDataMapper update = userDsGateway.update(userId, userDsModel);

    if (update == null) {
      return userPresenter.prepareFailView("NotFound");
    }

    UserResponseDTO accountResponseModel =
        new UserResponseDTO(
            update.getId(),
            update.getLogin(),
            update.getEmail(),
            update.getType().toString(),
            update.getCreationTime().toString());
    return userPresenter.prepareSuccessView(accountResponseModel);
  }

  @Override
  public UserResponseDTO deleteUser(UUID uuid) {

    Optional<UserDataMapper> opt = userDsGateway.getById(uuid);

    if (opt.isEmpty()) {
      return userPresenter.prepareFailView("NotFound");
    }

    userDsGateway.delete(opt.get());
    return userPresenter.prepareSuccessView(null);
  }
}
