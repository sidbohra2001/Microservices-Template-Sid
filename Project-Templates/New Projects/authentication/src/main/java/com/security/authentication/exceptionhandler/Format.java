package com.security.authentication.exceptionhandler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Format {
    private String status;
    private String message;
}
