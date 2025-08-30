package com.example.digg.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class CustomerId {
    private static final String REGEX = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String value;

    public CustomerId(final String value) {
        Validate.isTrue(isValid(value), "Invalid customer id");
        this.value = value;
    }

    public static CustomerId of(final String value) {
        return new CustomerId(value);
    }

    public static CustomerId generate() {
        return new CustomerId(UUID.randomUUID().toString());
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && PATTERN.matcher(value).matches();
    }
}
