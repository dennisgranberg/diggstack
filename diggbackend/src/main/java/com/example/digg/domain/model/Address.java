package com.example.digg.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Address {
    //TODO: Fix regex
    private static final String REGEX = "^[\\p{N}\\p{L}][\\p{N}\\p{L} .,\\-/#'()]{0,98}[\\p{N}\\p{L}]$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String value;

    public Address(final String value) {
        Validate.isTrue(isValid(value), "Invalid address");
        this.value = value;
    }

    public static Address of(final String value) {
        return new Address(value);
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && PATTERN.matcher(value).matches();
    }
}
