package com.practice.store.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponse<T> {
    private String status;
    private int code;
    private String details;
    private T errors;
}
