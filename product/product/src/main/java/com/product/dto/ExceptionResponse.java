package com.product.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String message;
    private Timestamp timestamp;
    private String httpCode;

}
