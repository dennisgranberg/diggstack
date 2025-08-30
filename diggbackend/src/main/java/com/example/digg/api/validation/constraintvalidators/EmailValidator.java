package com.example.digg.api.validation.constraintvalidators;

import com.example.digg.api.validation.constraints.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return value == null || com.example.digg.domain.model.Email.isValid(value);
    }
}
