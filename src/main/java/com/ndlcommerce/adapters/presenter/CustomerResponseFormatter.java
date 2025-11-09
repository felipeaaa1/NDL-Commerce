package com.ndlcommerce.adapters.presenter;

import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.exception.EntityAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.customer.CustomerPresenter;
import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerResponseFormatter implements CustomerPresenter {
    @Override
    public CustomerResponseDTO prepareSuccessView(CustomerResponseDTO customer) {
        if (customer != null) {
            LocalDateTime responseTime = LocalDateTime.parse(customer.getCreationTime());
            customer.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            return customer;
        } else {
            return null;
        }
    }

    @Override
    public CustomerResponseDTO prepareFailView(String error) {
        if ("UserNotExistsOrInactive".equals(error)) {
            throw new BusinessException("Usuário não encontrado ou esta inativo");
        } else if("NameNotValid".equals(error)){
            throw new BusinessException("Nome do Cliente não é válido");
        }
        else {
            throw new RuntimeException();
        }
    }


    @Override
    public List<CustomerResponseDTO> prepareListSuccessView(List<CustomerResponseDTO> list) {
        return List.of();
    }
}
