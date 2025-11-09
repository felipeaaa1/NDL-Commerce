package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.customer.CustomerDataMapper;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.entity.factory.CustomerFactory;
import com.ndlcommerce.entity.model.Customer;
import com.ndlcommerce.useCase.interfaces.customer.CustomerInputBoundary;
import com.ndlcommerce.useCase.interfaces.customer.CustomerPresenter;
import com.ndlcommerce.useCase.interfaces.customer.CustomerRegisterDsGateway;
import com.ndlcommerce.useCase.interfaces.user.UserPresenter;
import com.ndlcommerce.useCase.interfaces.user.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.customer.CustomerDbRequestDTO;
import com.ndlcommerce.useCase.request.customer.CustomerFilterDTO;
import com.ndlcommerce.useCase.request.customer.CustomerRequestDTO;
import com.ndlcommerce.useCase.request.customer.CustomerResponseDTO;
import com.ndlcommerce.useCase.request.user.UserDbRequestDTO;
import com.ndlcommerce.useCase.request.user.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerRegisterInteractor implements CustomerInputBoundary {

    final CustomerRegisterDsGateway customerRegisterDsGateway;
    final CustomerPresenter customerPresenter;
    final CustomerFactory customerFactory;
    final UserRegisterDsGateway userDsGateway;

    public CustomerRegisterInteractor(CustomerRegisterDsGateway customerRegisterDsGateway, CustomerPresenter customerPresenter, CustomerFactory customerFactory, UserRegisterDsGateway userDsGateway) {
        this.customerRegisterDsGateway = customerRegisterDsGateway;
        this.customerPresenter = customerPresenter;
        this.customerFactory = customerFactory;
        this.userDsGateway = userDsGateway;
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO requestModel) {

        Optional<UserDataMapper> opt = userDsGateway.getById(requestModel.getUserID());

        if (opt.isEmpty() || !opt.get().isEnabled()) {
            return customerPresenter.prepareFailView("UserNotExistsOrInactive");
        }

        Customer customer = customerFactory.create(
                requestModel.getUserID(),
                requestModel.getName(),
                requestModel.getContact(),
                requestModel.getAddress()
                );

        if (!customer.nameIsValid()){
            return customerPresenter.prepareFailView("NameNotValid");
        }
        CustomerDbRequestDTO dbRequestDTO = new CustomerDbRequestDTO(customer.getName(),customer.getContact()
        , customer.getAddress(), true, customer.getUserId());

        CustomerDataMapper save = customerRegisterDsGateway.save(dbRequestDTO);

        CustomerResponseDTO responseDTO = new CustomerResponseDTO(save.getId(),
                save.getUser().getLogin(),
                save.getName(),
                save.getContact(),
                save.getCreatedAt().toString()
                );

        return customerPresenter.prepareSuccessView(responseDTO);
    }

    @Override
    public List<?> list(CustomerFilterDTO requestModel, int page, int size) {
        return List.of();
    }

    @Override
    public CustomerResponseDTO getById(UUID userId) {
        return null;
    }

    @Override
    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerRequestDTO requestModel) {
        return null;
    }

    @Override
    public CustomerResponseDTO deleteCustomer(UUID uuid) {
        return null;
    }
}
