package com.example.digg.api.validation.constraintvalidators;

import com.example.digg.api.validation.constraints.Name;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return value == null || com.example.digg.domain.model.Name.isValid(value);
    }
}
