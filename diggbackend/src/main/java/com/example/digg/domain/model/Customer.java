package com.example.digg.domain.model;

import lombok.Builder;

import static java.util.Objects.requireNonNull;

@Builder

public record Customer(
        CustomerId customerId,
        Name name,
        Address address,
        Email email,
        Telephone telephone
) {
    public Customer {
        requireNonNull(customerId);
        requireNonNull(name);
    }
}
