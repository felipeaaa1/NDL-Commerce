package com.ndlcommerce.adapters.persistence;

import com.ndlcommerce.useCase.UserRegisterDsGateway;
import com.ndlcommerce.useCase.request.UserDsRequestModel;

public class JpaUser implements UserRegisterDsGateway {

    private final JpaUserRepository repository;

    public JpaUser(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public void save(UserDsRequestModel user) {
        UserDataMapper entity = new UserDataMapper(user.getLogin(), user.getPassword());
        repository.save(entity);
    }

}
