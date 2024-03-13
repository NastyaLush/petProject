package com.example.vkk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Photo (
        @JsonProperty("albumId") Long albumId,
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("url") String url,
        @JsonProperty("thumbnailUrl") String thumbnailUrl
){
}