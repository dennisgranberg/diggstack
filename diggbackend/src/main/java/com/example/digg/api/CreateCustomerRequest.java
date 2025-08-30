package com.example.digg.api;

import com.example.digg.api.validation.constraints.Address;
import com.example.digg.api.validation.constraints.Email;
import com.example.digg.api.validation.constraints.Name;
import com.example.digg.api.validation.constraints.Telephone;

public record CreateCustomerRequest(
        @Name
        String name,
        @Address
        String address,
        @Email
        String email,
        @Telephone
        String telephone
) {
}
