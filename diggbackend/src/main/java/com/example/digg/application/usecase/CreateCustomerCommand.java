package com.example.digg.application.usecase;

import com.example.digg.domain.model.Address;
import com.example.digg.domain.model.Email;
import com.example.digg.domain.model.Name;
import com.example.digg.domain.model.Telephone;
import lombok.Builder;

@Builder
public record CreateCustomerCommand(
        Name name,
        Address address,
        Email email,
        Telephone telephone
) {
}
