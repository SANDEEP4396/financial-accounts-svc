package com.financial.microservices.controller;

import com.financial.microservices.dto.CustomerDTO;
import com.financial.microservices.dto.ResponseDTO;
import com.financial.microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.financial.microservices.constants.AccountConstants.STATUS_200;
import static com.financial.microservices.constants.AccountConstants.STATUS_200_MESSAGE;
import static com.financial.microservices.constants.AccountConstants.STATUS_201;
import static com.financial.microservices.constants.AccountConstants.STATUS_201_MESSAGE;
import static com.financial.microservices.constants.AccountConstants.STATUS_500;
import static com.financial.microservices.constants.AccountConstants.STATUS_500_MESSAGE;

@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
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
    public ResponseEntity<ResponseDTO> createCustomerAccount(@RequestBody CustomerDTO customerDTO) {
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(STATUS_201, STATUS_201_MESSAGE));
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String phoneNumber) {
        CustomerDTO customerDTO = iAccountsService.fetchAccountDetailsWithPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/updateCustomerAccountDetails")
    public ResponseEntity<ResponseDTO> updateCustomerAccountDetails(@RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountsService.updateCustomerAccountDetails(customerDTO);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, STATUS_200_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(STATUS_500, STATUS_500_MESSAGE));
        }
    }

    @DeleteMapping("/deleteCustomerAccountDetails")
    public ResponseEntity<ResponseDTO> deleteCustomerAccountDetails(@RequestParam String phoneNumber) {
        boolean isDeleted = iAccountsService.deleteCustomerAccountDetails(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDTO(STATUS_200, STATUS_200_MESSAGE));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(STATUS_500, STATUS_500_MESSAGE));
        }
    }
}
