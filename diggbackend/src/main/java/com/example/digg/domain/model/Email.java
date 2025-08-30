package com.example.digg.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Email {
    private static final String REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String value;

    public Email(final String value) {
        Validate.isTrue(isValid(value), "Invalid email");
        this.value = value;
    }

    public static Email of(final String value) {
        return new Email(value);
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && PATTERN.matcher(value).matches();
    }
}
