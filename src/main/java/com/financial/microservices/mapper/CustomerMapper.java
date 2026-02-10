package com.financial.microservices.mapper;

import com.financial.microservices.dto.CustomerDTO;
import com.financial.microservices.entity.Customer;

import java.time.LocalDateTime;

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
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("System");
        return customer;
    }
}
