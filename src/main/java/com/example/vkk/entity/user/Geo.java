package com.example.vkk.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Geo(
        @JsonProperty("lat") Double lat,
        @JsonProperty("lng") Double lng
) {
}
