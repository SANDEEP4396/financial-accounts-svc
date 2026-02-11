package com.financial.accounts.microservice.controller;

import com.financial.accounts.microservice.dto.CustomerDTO;
import com.financial.accounts.microservice.dto.ResponseDTO;
import com.financial.accounts.microservice.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
// @AllArgsConstructor is a Lombok annotation that generates a constructor with one parameter for each field in the class.
// In this case, it will generate a constructor that takes an IAccountsService as a parameter and assigns it to the iAccountsService field.
// This allows for dependency injection of the IAccountsService when creating an instance of AccountsController.
//Without @AllArgsConstructor, you would need to manually create a constructor like this:
// public AccountsController(IAccountsService iAccountsService) {
//     this.iAccountsService = iAccountsService;
// } Or use @Autowired annotation on the field for Spring to inject the dependency.
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to home page";
    }

    @GetMapping("/customerDetails")
    public String customerDetails() {
        return "Welcome to customer details page";
    }

    @PostMapping("/createCustomerAccount")
    public ResponseEntity<ResponseDTO> createCustomerAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, STATUS_201_MESSAGE));
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "^[2-9][0-9]{10}$", message = "Phone number must be 10 digits")
                                                           String phoneNumber) {
        CustomerDTO customerDTO = iAccountsService.fetchAccountDetailsWithPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customerDTO);
    }

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
}
