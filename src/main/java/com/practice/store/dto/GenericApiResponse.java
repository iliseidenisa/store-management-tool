package com.practice.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericApiResponse<T> {
    private String status;
    private int code;
    private String message;
    private T data;
}
