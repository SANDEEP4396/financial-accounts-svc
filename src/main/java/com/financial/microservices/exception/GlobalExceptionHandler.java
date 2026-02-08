package com.financial.microservices.exception;

import com.financial.microservices.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerDetailsAlreadyExist.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerDetailsAlreadyExist(final CustomerDetailsAlreadyExist ex,
                                                                              final WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(

                ex.getMessage(),
                // request.getDescription(false) returns the URI of the request that caused the exception, if its true it returns the URI along with the query parameters, IP etc.
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
