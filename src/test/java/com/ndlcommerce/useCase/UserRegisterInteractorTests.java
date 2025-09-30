package com.ndlcommerce.useCase;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.ndlcommerce.entity.UserType;
import com.ndlcommerce.entity.factory.UserFactory;
import com.ndlcommerce.entity.model.CommonUser;
import com.ndlcommerce.entity.model.User;
import com.ndlcommerce.useCase.request.UserDbRequestDTO;
import com.ndlcommerce.useCase.request.UserRequestDTO;
import com.ndlcommerce.useCase.request.UserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRegisterInteractorTests {

  private UserRegisterDsGateway userDsGateway;
  private UserPresenter userPresenter;
  private UserFactory userFactory;
  private UserRegisterInteractor interactor;

  @BeforeEach
  void setUp() {
    userDsGateway = mock(UserRegisterDsGateway.class);
    userPresenter = mock(UserPresenter.class);
    userFactory = mock(UserFactory.class);
    interactor = new UserRegisterInteractor(userDsGateway, userPresenter, userFactory);
  }

  @Test
  void givenBaeldungUserAndAa123456Password_whenCreate_thenSaveItAndPrepareSuccessView() {
    User user = new CommonUser("baeldung", "baeldung", UserType.COMMON, "Senha1234");
    UserRequestDTO userRequestDTO =
        new UserRequestDTO(user.getName(), user.getType(), user.getPassword());

    when(userFactory.create(anyString(), anyString(), any(), anyString()))
        .thenReturn(
            new CommonUser(user.getName(), user.getEmail(), user.getType(), user.getPassword()));
    when(userDsGateway.existsByName(anyString())).thenReturn(false);

    interactor.create(userRequestDTO);

    verify(userDsGateway, times(1)).save(any(UserDbRequestDTO.class));
    verify(userPresenter, times(1)).prepareSuccessView(any(UserResponseDTO.class));
  }
}
