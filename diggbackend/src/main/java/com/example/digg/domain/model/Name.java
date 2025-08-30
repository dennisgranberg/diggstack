package com.example.digg.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Name {
    private static final String REGEX = "^[\\p{L}][\\p{L} .'-]{0,98}[\\p{L}]$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String value;

    public Name(final String value) {
        Validate.isTrue(isValid(value), "Invalid name");
        this.value = value;
    }

    public static Name of(final String value) {
        return new Name(value);
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && PATTERN.matcher(value).matches();
    }
}
