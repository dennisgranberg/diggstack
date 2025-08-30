package com.example.digg.infrastructure;

import com.example.digg.domain.CustomerRepository;
import com.example.digg.domain.model.Address;
import com.example.digg.domain.model.Customer;
import com.example.digg.domain.model.CustomerId;
import com.example.digg.domain.model.Email;
import com.example.digg.domain.model.Name;
import com.example.digg.domain.model.Telephone;
import com.example.digg.infrastructure.model.CustomerEntity;
import com.example.digg.util.RepositoryError;
import com.example.digg.util.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerStubDatabase implements CustomerRepository {
    private List<CustomerEntity> customers = new ArrayList<>();

    @Override
    public Result<RepositoryError, com.example.digg.domain.model.Customer> createCustomer(final com.example.digg.domain.model.Customer customer) {
        requireNonNull(customer);

        final CustomerEntity customerEntity = CustomerEntity.builder()
                .id(customer.customerId().getValue())
                .name(customer.name().getValue())
                .address(customer.address().getValue())
                .email(customer.email().getValue())
                .telephone(customer.telephone().getValue())
                .build();

        //Simulate database operation
        try {
            customers.add(customerEntity);
            return Result.success(customer);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.reject(RepositoryError.DATABASE_ERROR);
        }
    }

    @Override
    public Result<RepositoryError, List<Customer>> getCustomers() {
        try {
            return customers.stream()
                    .map(this::mapToCustomer)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Result::success));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.reject(RepositoryError.DATABASE_ERROR);
        }
    }


    private Customer mapToCustomer(final CustomerEntity customerEntity) {
        return Customer.builder()
                .customerId(CustomerId.of(customerEntity.getId()))
                .name(Name.of(customerEntity.getName()))
                .address(Address.of(customerEntity.getAddress()))
                .email(Email.of(customerEntity.getEmail()))
                .telephone(Telephone.of(customerEntity.getTelephone()))
                .build();
    }

}
