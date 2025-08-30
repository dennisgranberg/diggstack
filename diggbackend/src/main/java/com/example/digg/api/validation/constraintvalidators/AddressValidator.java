package com.example.digg.api.validation.constraintvalidators;

import com.example.digg.api.validation.constraints.Address;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return value == null || com.example.digg.domain.model.Address.isValid(value);
    }
}
