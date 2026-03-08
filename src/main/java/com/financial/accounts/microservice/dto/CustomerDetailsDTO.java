package com.financial.accounts.microservice.dto;

import com.financial.accounts.microservice.dto.cards.CardDTO;
import com.financial.accounts.microservice.dto.loans.LoanDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema holds the details of the customer cards, loans and accounts information")
public class CustomerDetailsDTO {
    @Schema(
            description = "First name of the customer",
            example = "John"
    )
    @NotEmpty(message = "First name must not be empty")
    @Size(min = 5, max = 30, message = "First name must be between 5 and 30 characters")
    private String firstName;

    @Schema(
            description = "Last name of the customer",
            example = "Doe"
    )
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;

    @Schema(
            description = "Email address of the customer",
            example = "Test@email.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Phone number of the customer",
            example = "9876543210"
    )
    @Pattern(regexp = "^[2-9][0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Schema(
            description = "Accounts information of the customer"
    )
    private AccountsDTO accountsDTO;

    @Schema(
            description = "Loans information of the customer"
    )
    private LoanDTO loanDTO;

    @Schema(
            description = "Cards information of the customer"
    )
    private CardDTO cardDTO;
}
