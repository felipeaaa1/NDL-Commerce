package com.ndlcommerce.adapters.persistence.customer;

import com.ndlcommerce.adapters.persistence.user.JpaUserRepository;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.exception.BusinessException;
import com.ndlcommerce.useCase.interfaces.customer.CustomerRegisterDsGateway;
import com.ndlcommerce.useCase.request.customer.CustomerDbRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaCustomer implements CustomerRegisterDsGateway {

    private final JpaCustomerRepository repository;
    private final JpaUserRepository userRepository;
    private final SecurityFilter securityFilter;

    public JpaCustomer(JpaCustomerRepository repository, JpaUserRepository userRepository, SecurityFilter securityFilter) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.securityFilter = securityFilter;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByUser(UUID userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public CustomerDataMapper save(CustomerDbRequestDTO requestDTO) {
        UserDataMapper user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new BusinessException("não era para essa excessão ter chegado aqui. coloca no interactor."));
        UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

        CustomerDataMapper entity = new CustomerDataMapper(
                user,
                requestDTO.getName(),
                requestDTO.getContact(),
                requestDTO.getAddress(),
                userLogado != null ? userLogado.getId() : null
        );

        return repository.save(entity);
    }
}

