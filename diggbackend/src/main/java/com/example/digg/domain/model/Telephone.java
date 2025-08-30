package com.example.digg.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Telephone {
    private static final String REGEX = "^\\+?[0-9][0-9 .\\-()]{1,23}[0-9]$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String value;

    public Telephone(final String value) {
        Validate.isTrue(isValid(value), "Invalid telephone number");
        this.value = value;
    }

    public static Telephone of(final String value) {
        return new Telephone(value);
    }

    public static boolean isValid(String value) {
        return StringUtils.isNotBlank(value) && PATTERN.matcher(value).matches();
    }
}
