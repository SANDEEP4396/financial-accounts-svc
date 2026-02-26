package com.financial.accounts.microservice.controller;

import com.financial.accounts.microservice.dto.CustomerDTO;
import com.financial.accounts.microservice.dto.ErrorResponseDTO;
import com.financial.accounts.microservice.dto.ResponseDTO;
import com.financial.accounts.microservice.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_200;
import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_200_MESSAGE;
import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_201;
import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_201_MESSAGE;
import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_500;
import static com.financial.accounts.microservice.constants.AccountConstants.STATUS_500_MESSAGE;

@Tag(
        name = "Customer Account Management",
        description = "This controller provides endpoints for creating, retrieving, updating, and deleting customer accounts"
)
@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
// @AllArgsConstructor is a Lombok annotation that generates a constructor with one parameter for each field in the class.
// In this case, it will generate a constructor that takes an IAccountsService as a parameter and assigns it to the iAccountsService field.
// This allows for dependency injection of the IAccountsService when creating an instance of AccountsController.
//Without @AllArgsConstructor, you would need to manually create a constructor like this:
// public AccountsController(IAccountsService iAccountsService) {
//     this.iAccountsService = iAccountsService;
// } Or use @Autowired annotation on the field for Spring to inject the dependency.
//@AllArgsConstructor
//@NoArgsConstructor
@Validated
public class AccountsController {

    @Autowired
    private Environment environment;
    private final IAccountsService iAccountsService;
    @Value("${build.version}")
    private String buildVersion;

    public AccountsController(final IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Operation(
            summary = "Welcome to home page, along with build version",
            description = "This endpoint serves as a welcome message for the home page of the accounts microservice. " +
                    "Also, it provides the build version of the application, which can be useful for debugging and monitoring purposes.",
            tags = {"Home"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully accessed the home page"
    )
    @GetMapping("/home")
    public String buildInfo() {
        return "Welcome to home page: " + buildVersion;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Account created successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to invalid input",
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
            summary = "Create a new customer account",
            description = "This endpoint allows you to create a new customer account by providing the necessary details in the request body.",
            tags = {"Customer Account Management"}
    )
    @PostMapping("/createCustomerAccount")
    public ResponseEntity<ResponseDTO> createCustomerAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, STATUS_201_MESSAGE));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer account details retrieved successfully"),
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
            summary = "Fetch customer account details by phone number",
            description = "This endpoint allows you to retrieve the account details of a customer using their phone number as a query parameter.",
            tags = {"Customer Account Management"}
    )
    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "^[2-9][0-9]{10}$", message = "Phone number must be 10 digits")
                                                           String phoneNumber) {
        CustomerDTO customerDTO = iAccountsService.fetchAccountDetailsWithPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer account details updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request due to invalid input",
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
            summary = "Update customer account details",
            description = "This endpoint allows you to update the account details of a customer by providing the updated information in the request body.",
            tags = {"Customer Account Management"}
    )
    @PutMapping("/updateCustomerAccountDetails")
    public ResponseEntity<ResponseDTO> updateCustomerAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountsService.updateCustomerAccountDetails(customerDTO);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, STATUS_200_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(STATUS_500, STATUS_500_MESSAGE));
        }
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Customer account details deleted successfully"),
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
            summary = "Delete customer account details",
            description = "This endpoint allows you to delete the account details of a customer using their phone number as a query parameter.",
            tags = {"Customer Account Management"}
    )
    @DeleteMapping("/deleteCustomerAccountDetails")
    public ResponseEntity<ResponseDTO> deleteCustomerAccountDetails(@RequestParam
                                                                    @Pattern(regexp = "^[2-9][0-9]{10}$", message = "Phone number must be 10 digits")
                                                                    String phoneNumber) {
        boolean isDeleted = iAccountsService.deleteCustomerAccountDetails(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, STATUS_200_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(STATUS_500, STATUS_500_MESSAGE));
        }
    }

    @Operation(
            summary = "Get Java version",
            description = "This endpoint allows you to retrieve the Java version that the application is running on.",
            tags = {"System Information"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Java version retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("java.version"));
        //Clie Can try "JAVA_HOME" as well to get the Java home directory and "MAVEN_HOME" to get the Maven home directory.
    }
}
