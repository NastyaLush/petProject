package com.example.vkk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Todo(
        @JsonProperty("userId") Long userId,
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("completed") Boolean completed
) {
}
