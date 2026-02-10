package com.financial.microservices.service;

import com.financial.microservices.dto.CustomerDTO;

public interface IAccountsService {
   /*
        * This method is responsible for creating a new account for a customer. It takes a CustomerDTO object as input,
        * which contains the necessary information about the customer.
        * The method will handle the logic for creating the account and associating it with the customer.
        * @param customerDTO - The data transfer object containing customer information needed to create an account.
    */
    void createAccount(CustomerDTO customerDTO);
    //TODO: This method is responsible for fetching the account details of a customer based on their unique identifier (customerId).
    void fetchAccountDetails(Long customerId);
    CustomerDTO fetchAccountDetailsWithPhoneNumber(String phoneNumber);
    boolean updateCustomerAccountDetails(CustomerDTO customerDTO);
    boolean deleteCustomerAccountDetails(String phoneNumber);
}
