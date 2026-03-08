package com.financial.accounts.microservice.service.impl;

import com.financial.accounts.microservice.dto.AccountsDTO;
import com.financial.accounts.microservice.dto.CustomerDetailsDTO;
import com.financial.accounts.microservice.dto.cards.CardDTO;
import com.financial.accounts.microservice.dto.loans.LoanDTO;
import com.financial.accounts.microservice.entity.Accounts;
import com.financial.accounts.microservice.entity.Customer;
import com.financial.accounts.microservice.exception.ResourceNotFoundException;
import com.financial.accounts.microservice.repository.AccountsRepository;
import com.financial.accounts.microservice.repository.CustomerRepository;
import com.financial.accounts.microservice.service.ICustomerService;
import com.financial.accounts.microservice.service.client.CardsFeignClient;
import com.financial.accounts.microservice.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.financial.accounts.microservice.mapper.AccountsMapper.mapToAccountsDTO;
import static com.financial.accounts.microservice.mapper.CustomerMapper.mapToCustomerDetailsDTO;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /*
     * This method is responsible for fetching the customer details based on the provided phone number.
     * @param phoneNumber The phone number of the customer for whom the details are to be fetched.
     * @return CustomerDetailsDTO containing the details of the customer, including their accounts, loans, and cards information.
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone number ", phoneNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customer id ", customer.getCustomerId().toString()));
        CustomerDetailsDTO customerDetailsDTO = mapToCustomerDetailsDTO(new CustomerDetailsDTO(), customer);
        customerDetailsDTO.setAccountsDTO(mapToAccountsDTO(accounts, new AccountsDTO()));

        final ResponseEntity<LoanDTO> loanDTOResponseEntity = loansFeignClient.fetchLoanDetails(phoneNumber);
        if (loanDTOResponseEntity.getStatusCode().is2xxSuccessful()) {
            customerDetailsDTO.setLoanDTO(loanDTOResponseEntity.getBody());
        }

        final ResponseEntity<CardDTO> cardDTOResponseEntity = cardsFeignClient.fetchCardDetails(phoneNumber);
        if (cardDTOResponseEntity.getStatusCode().is2xxSuccessful()) {
            customerDetailsDTO.setCardDTO(cardDTOResponseEntity.getBody());
        }
        return customerDetailsDTO;
    }
}
