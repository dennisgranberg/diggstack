package com.example.digg.infrastructure.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static org.apache.commons.lang3.Validate.notBlank;


@Getter
@EqualsAndHashCode
public class CustomerEntity {
    private final String id;
    private final String name;
    private final String address;
    private final String email;
    private final String telephone;

    @Builder
    public CustomerEntity(final String id, final String name,
                          final String address,
                          final String email,
                          final String telephone) {
        this.id = notBlank(id);
        this.name = notBlank(name);
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }
}
