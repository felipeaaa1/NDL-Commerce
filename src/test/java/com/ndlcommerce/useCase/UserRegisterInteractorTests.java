package com.ndlcommerce.useCase;


import com.ndlcommerce.entity.factory.UserFactory;
import com.ndlcommerce.entity.model.CommonUser;
import com.ndlcommerce.entity.model.User;
import com.ndlcommerce.useCase.request.UserDsRequestModel;
import com.ndlcommerce.useCase.request.UserRequestModel;
import com.ndlcommerce.useCase.request.UserResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        User user = new CommonUser("baeldung", "Aa123456");
        UserRequestModel userRequestModel = new UserRequestModel(user.getName(), user.getPassword());

        when(userFactory.create(anyString(), anyString()))
                .thenReturn(new CommonUser(user.getName(), user.getPassword()));
        when(userDsGateway.existsByName(anyString()))
                .thenReturn(false);

        interactor.create(userRequestModel);

        verify(userDsGateway, times(1)).save(any(UserDsRequestModel.class));
        verify(userPresenter, times(1)).prepareSuccessView(any(UserResponseModel.class));
    }

}
