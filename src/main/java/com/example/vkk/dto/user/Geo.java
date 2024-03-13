package com.example.vkk.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Geo (
        @JsonProperty("lat") Double lat,
        @JsonProperty("lng") Double lng
){
}
