package com.financial.accounts.microservice.service;

import com.financial.accounts.microservice.dto.CustomerDetailsDTO;

public interface ICustomerService {

    /*
        * This method is responsible for fetching the customer details based on the provided phone number.
        * @param phoneNumber The phone number of the customer for whom the details are to be fetched.
        * @return CustomerDetailsDTO containing the details of the customer, including their accounts, loans, and cards information.
     */
    CustomerDetailsDTO fetchCustomerDetails(String phoneNumber);
}
