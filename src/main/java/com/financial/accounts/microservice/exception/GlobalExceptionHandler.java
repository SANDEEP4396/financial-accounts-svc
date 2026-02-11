package com.financial.accounts.microservice.exception;

import com.financial.accounts.microservice.dto.ErrorResponseDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(final Exception ex, final WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(final ResourceNotFoundException ex,
                                                                              final WebRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(

                ex.getMessage(),
                // request.getDescription(false) returns the URI of the request that caused the exception, if its true it returns the URI along with the query parameters, IP etc.
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                     final @NonNull HttpHeaders headers,
                                     final @NonNull HttpStatusCode statusCode,
                                     final @NonNull WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();
        validationErrors.forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
