package com.security.authentication.handlers;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Format {
    private String statusCode;
    private String message;
}
