package com.financial.microservices.service.impl;

import com.financial.microservices.dto.CustomerDTO;
import com.financial.microservices.entity.Accounts;
import com.financial.microservices.entity.Customer;
import com.financial.microservices.exception.CustomerDetailsAlreadyExist;
import com.financial.microservices.repository.AccountsRepository;
import com.financial.microservices.repository.CustomerRepository;
import com.financial.microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.financial.microservices.constants.AccountConstants.ADDRESS;
import static com.financial.microservices.constants.AccountConstants.CHECKING;
import static com.financial.microservices.mapper.CustomerMapper.mapToCustomer;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(final CustomerDTO customerDTO) {
        validateCustomerDetails(customerDTO);
        final Customer customer = mapToCustomer(new Customer(), customerDTO);
        final Customer createdCustomer = customerRepository.save(customer);
        createAccountForCustomer(createdCustomer);
    }

    private void validateCustomerDetails(final CustomerDTO customerDTO) {
        if (customerRepository.existsByEmailOrPhoneNumber(customerDTO.getEmail(), customerDTO.getPhoneNumber())) {
            throw new CustomerDetailsAlreadyExist("Customer with the same email or phone number is already registered.");
        }
    }

    @Override
    public void fetchAccountDetails(final Long customerId) {

    }

    private void createAccountForCustomer(final Customer customer) {
        final Accounts newAccount = new Accounts();
        final Long randAccountNumber = (long) (Math.random() * 1000000000L);
        newAccount.setAccountNumber(randAccountNumber);
        newAccount.setAccountType(CHECKING);
        newAccount.setBranchAddress(ADDRESS);
        newAccount.setCustomerId(customer.getCustomerId());
        accountsRepository.save(newAccount);
    }
}
