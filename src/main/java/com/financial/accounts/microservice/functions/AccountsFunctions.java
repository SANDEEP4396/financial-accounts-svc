package com.financial.accounts.microservice.functions;

import com.financial.accounts.microservice.service.IAccountsService;
import com.financial.accounts.microservice.service.impl.AccountsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunicationStatus(final IAccountsService accountsService) {
        return accountNumber -> {
            LOGGER.info("Received communication status update for account number: {}", accountNumber);
            // Here you can implement the logic to update the communication status in your database or perform any other necessary actions.
            accountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
