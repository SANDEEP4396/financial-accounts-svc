package com.financial.microservices.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account type must not be empty")
    private String accountType;
    @NotEmpty(message = "Account type must not be empty")
    private String branchAddress;
}
