package com.financial.accounts.microservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema holds the details of the accounts such as account number, account type and branch address"
)
public class AccountsDTO {

    @Schema(
            description = "Account number of the customer",
            example = "1234567890"
    )
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of the customer",
            example = "Savings"
    )
    @NotEmpty(message = "Account type must not be empty")
    private String accountType;

    @Schema(
            description = "Branch address of the customer",
            example = "123 Main St, Anytown, USA"
    )
    @NotEmpty(message = "Account type must not be empty")
    private String branchAddress;
}
