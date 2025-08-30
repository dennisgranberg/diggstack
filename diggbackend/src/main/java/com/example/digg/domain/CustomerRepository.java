package com.example.digg.domain;

import com.example.digg.domain.model.Customer;
import com.example.digg.util.RepositoryError;
import com.example.digg.util.Result;

import java.util.List;

public interface CustomerRepository {
    Result<RepositoryError, Customer> createCustomer(Customer customer);
    Result<RepositoryError, List<Customer>> getCustomers();
}
