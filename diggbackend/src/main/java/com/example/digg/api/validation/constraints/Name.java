package com.example.digg.api.validation.constraints;

import com.example.digg.api.validation.constraintvalidators.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
    String message() default "must be a valid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
