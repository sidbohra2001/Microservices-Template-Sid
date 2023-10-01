package com.security.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterResponseDto {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Role")
    private String role;
}
