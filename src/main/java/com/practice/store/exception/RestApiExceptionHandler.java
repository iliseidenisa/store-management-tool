package com.practice.store.exception;

import com.practice.store.dto.GenericErrorResponse;
import com.practice.store.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        GenericErrorResponse error = ResponseUtil.error(
                HttpStatus.NOT_FOUND,
                "Resource not found", ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class, ValidationException.class})
    public ResponseEntity<?> handleValidationExceptions(Exception ex) {
        log.error("Validation error: {}", ex.getMessage());
        GenericErrorResponse error = ResponseUtil.error(
                HttpStatus.BAD_REQUEST,
                "Validation error", ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<?> handleInternalError(Exception ex) {
        log.error("Error occurred: {}", ex.getMessage(), ex);
        GenericErrorResponse errorMessage = ResponseUtil.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error", ex.getMessage()
        );

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
