package com.example.vkk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostComment (
        @JsonProperty("postId") Long postId,
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("body") String body
) {
}
