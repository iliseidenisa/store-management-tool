package com.practice.store.util;

import com.practice.store.dto.GenericApiResponse;
import com.practice.store.dto.GenericErrorResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

    public static <T> GenericApiResponse<T> success(HttpStatus status, T data) {
        return new GenericApiResponse<>("success", status.value(), status.getReasonPhrase(), data);
    }

    public static <T> GenericErrorResponse<T> error(HttpStatus status, String details, T errors) {
        return new GenericErrorResponse<>("error", status.value(), status.getReasonPhrase(), errors);
    }
}
