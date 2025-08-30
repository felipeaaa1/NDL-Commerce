package com.ndlcommerce.adapters;

import com.ndlcommerce.adapters.presenter.UserResponseFormatter;
import com.ndlcommerce.useCase.request.UserResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseFormatterTest {

    UserResponseFormatter userResponseFormatter;

    @BeforeEach
    void setup(){
        this.userResponseFormatter = new UserResponseFormatter();
    }

    @Test
    void givenDateAnd3HourTime_whenPrepareSuccessView_thenReturnOnly3HourTime() {
        UserResponseModel modelResponse = new UserResponseModel("baeldung", "2020-12-20T03:00:00.000");
        UserResponseModel formattedResponse = userResponseFormatter.prepareSuccessView(modelResponse);

        assertThat(formattedResponse.getCreationTime()).isEqualTo("03:00:00");
    }
}
