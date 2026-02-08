package com.financial.microservices.controller;

import com.financial.microservices.dto.CustomerDTO;
import com.financial.microservices.dto.ResponseDTO;
import com.financial.microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.financial.microservices.constants.AccountConstants.STATUS_201;
import static com.financial.microservices.constants.AccountConstants.STATUS_201_MESSAGE;

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
}
