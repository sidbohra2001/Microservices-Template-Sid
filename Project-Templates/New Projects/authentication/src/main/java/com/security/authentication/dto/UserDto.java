package com.security.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    @JsonProperty("Username")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Username must be only letters")
    private String username;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("Role")
    private String role;
}
