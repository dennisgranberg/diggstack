package com.example.digg.api.validation.constraintvalidators;

import com.example.digg.api.validation.constraints.Telephone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return value == null || com.example.digg.domain.model.Telephone.isValid(value);
    }
}
