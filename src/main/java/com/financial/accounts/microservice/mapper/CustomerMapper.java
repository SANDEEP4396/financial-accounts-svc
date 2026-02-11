package com.financial.accounts.microservice.mapper;

import com.financial.accounts.microservice.dto.CustomerDTO;
import com.financial.accounts.microservice.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(final CustomerDTO customerDTO, final Customer customer) {
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(final Customer customer, final CustomerDTO customerDTO) {
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }
}
