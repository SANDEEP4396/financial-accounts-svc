package com.financial.microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 5, max = 30, message = "First name must be between 5 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^[2-9][0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;
    private AccountsDTO accountsDTO;
}
