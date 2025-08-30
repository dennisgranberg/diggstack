package com.example.digg.application.usecase;

import com.example.digg.domain.model.Customer;
import com.example.digg.util.CreateCustomerError;
import com.example.digg.util.Result;

public interface CreateCustomerUseCase {
    Result<CreateCustomerError, Customer> execute(CreateCustomerCommand command);

    static CreateCustomerCommand.CreateCustomerCommandBuilder builder() {
        return new CreateCustomerCommand.CreateCustomerCommandBuilder();
    }
}
