package com.financial.microservices.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AccountsDTO accountsDTO;
}
