package com.financial.microservices.service.impl;

import com.financial.microservices.dto.AccountsDTO;
import com.financial.microservices.dto.CustomerDTO;
import com.financial.microservices.entity.Accounts;
import com.financial.microservices.entity.Customer;
import com.financial.microservices.exception.CustomerDetailsAlreadyExist;
import com.financial.microservices.exception.ResourceNotFoundException;
import com.financial.microservices.repository.AccountsRepository;
import com.financial.microservices.repository.CustomerRepository;
import com.financial.microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.financial.microservices.constants.AccountConstants.ADDRESS;
import static com.financial.microservices.constants.AccountConstants.CHECKING;
import static com.financial.microservices.mapper.AccountsMapper.mapToAccounts;
import static com.financial.microservices.mapper.AccountsMapper.mapToAccountsDTO;
import static com.financial.microservices.mapper.CustomerMapper.mapToCustomer;
import static com.financial.microservices.mapper.CustomerMapper.mapToCustomerDTO;

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

    @Override
    public CustomerDTO fetchAccountDetailsWithPhoneNumber(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number ", phoneNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id ", customer.getCustomerId().toString()));
       CustomerDTO customerDTO =  mapToCustomerDTO(new CustomerDTO(), customer);
       customerDTO.setAccountsDTO(mapToAccountsDTO(accounts, new AccountsDTO()));
       return customerDTO;
    }

    @Override
    public boolean updateCustomerAccountDetails(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO getAccountDetails = customerDTO.getAccountsDTO();
        if(getAccountDetails!=null){
            Accounts accounts = accountsRepository.findById(getAccountDetails.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "customer id ", getAccountDetails.getAccountNumber().toString())
                    );
            mapToAccounts(getAccountDetails, accounts);
            accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer", "customer id ", customerId.toString())
                    );
            mapToCustomer(customer, customerDTO);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCustomerAccountDetails(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number ", phoneNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
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
