package com.example.digg.api.validation.constraints;

import com.example.digg.api.validation.constraintvalidators.AddressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {
    String message() default "must be a valid address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
