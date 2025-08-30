package com.example.digg.api;

import lombok.Builder;

@Builder
public record Customer(
        String id,
        String name,
        String address,
        String email,
        String telephone
) {
}
