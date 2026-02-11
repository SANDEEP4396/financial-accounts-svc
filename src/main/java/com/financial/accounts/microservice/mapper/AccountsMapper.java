package com.financial.accounts.microservice.mapper;

import com.financial.accounts.microservice.dto.AccountsDTO;
import com.financial.accounts.microservice.entity.Accounts;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDTO(final Accounts accounts, final AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Accounts mapToAccounts(final AccountsDTO accountsDTO, final Accounts accounts) {
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }
}
