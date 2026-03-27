package com.financial.accounts.microservice.controller;

import com.financial.accounts.microservice.dto.CustomerDetailsDTO;
import com.financial.accounts.microservice.dto.ErrorResponseDTO;
import com.financial.accounts.microservice.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Customer Accounts",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE customer account details"
)
@RestController
@Validated
@RequestMapping(path = "/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerService customerService;

    public CustomerController(final ICustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer details retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to invalid phone number format",

                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDTO.class
                                    )
                            )),
                    @ApiResponse(responseCode = "404", description = "Customer account not found",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDTO.class
                                    )
                            )),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDTO.class
                                    )
                            ))
            }
    )
    @Operation(
            summary = "Fetch customer details by phone number",
            description = "REST API to fetch customer details based on phone number",
            tags = {"Customer Accounts"}
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("X-Correlation-ID") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "^[2-9][0-9]{9}$", message = "Phone number must be 10 digits")
                                                                   String phoneNumber) {
       LOGGER.debug("fetchCustomerDetails API called");
        // Implementation to fetch customer details based on phone number
        final CustomerDetailsDTO customerDetailsDTO = customerService.fetchCustomerDetails(phoneNumber, correlationId);
        LOGGER.debug("Fetching customer details for phone number: {}", phoneNumber);
        return ResponseEntity.ok(customerDetailsDTO);
    }
}
