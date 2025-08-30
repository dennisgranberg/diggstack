package com.example.digg.application;

import com.example.digg.application.usecase.CreateCustomerCommand;
import com.example.digg.application.usecase.CreateCustomerUseCase;
import com.example.digg.application.usecase.GetCustomersQuery;
import com.example.digg.application.usecase.GetCustomersUseCase;
import com.example.digg.domain.CustomerRepository;
import com.example.digg.domain.model.Customer;
import com.example.digg.domain.model.CustomerId;
import com.example.digg.util.CreateCustomerError;
import com.example.digg.util.GetCustomersError;
import com.example.digg.util.RepositoryError;
import com.example.digg.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class CustomerService implements CreateCustomerUseCase, GetCustomersUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Result<CreateCustomerError, Customer> execute(final CreateCustomerCommand command) {
        requireNonNull(command);

        final Result<RepositoryError, Customer> result = customerRepository.createCustomer(mapToCustomer(command));

        return switch (result) {
            case Result.Success<RepositoryError, Customer> success -> Result.success(success.value());
            case Result.Reject<RepositoryError, Customer> reject -> handleCreateCustomerReject(reject.value());
        };
    }

    @Override
    public Result<GetCustomersError, List<Customer>> execute(final GetCustomersQuery query) {
        requireNonNull(query);

        return switch (customerRepository.getCustomers()) {
            case Result.Success<RepositoryError, List<Customer>> success -> Result.success(success.value());
            case Result.Reject<RepositoryError, List<Customer>> reject -> handleGetCustomersReject(reject.value());
        };
    }

    private Customer mapToCustomer(final CreateCustomerCommand command) {
        return Customer.builder()
                .customerId(CustomerId.generate())
                .name(command.name())
                .email(command.email())
                .address(command.address())
                .telephone(command.telephone())
                .build();
    }

    private Result<CreateCustomerError, Customer> handleCreateCustomerReject(final RepositoryError error) {
        return switch (error) {
            case NOT_FOUND -> throw new IllegalStateException("Not Applicable");
            case DATABASE_ERROR -> Result.reject(CreateCustomerError.DATABASE_FAILURE);
        };
    }

    private Result<GetCustomersError, List<Customer>> handleGetCustomersReject(final RepositoryError error) {
        return switch (error) {
            case NOT_FOUND -> throw new IllegalStateException("Not Applicable");
            case DATABASE_ERROR -> Result.reject(GetCustomersError.DATABASE_FAILURE);
        };
    }

}
