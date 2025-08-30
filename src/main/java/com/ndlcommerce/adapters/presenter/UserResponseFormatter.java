package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.useCase.UserPresenter;
import com.ndlcommerce.useCase.request.UserResponseModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserResponseFormatter implements UserPresenter {

  @Override
  public UserResponseModel prepareSuccessView(UserResponseModel response) {
    LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
    response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
    return response;
  }

  @Override
  public UserResponseModel prepareFailView(String error) {
    throw new ResponseStatusException(HttpStatus.CONFLICT, error);
  }
}
