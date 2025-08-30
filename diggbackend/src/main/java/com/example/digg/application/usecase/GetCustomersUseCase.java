package com.example.digg.application.usecase;

import com.example.digg.domain.model.Customer;
import com.example.digg.util.GetCustomersError;
import com.example.digg.util.Result;

import java.util.List;

public interface GetCustomersUseCase {
    Result<GetCustomersError, List<Customer>> execute(GetCustomersQuery query);

    static GetCustomersQuery.GetCustomersQueryBuilder builder() {
        return new GetCustomersQuery.GetCustomersQueryBuilder();
    }
}
