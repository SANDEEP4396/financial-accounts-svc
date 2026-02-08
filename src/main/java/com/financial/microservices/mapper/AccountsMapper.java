package com.financial.microservices.mapper;

import com.financial.microservices.dto.AccountsDTO;
import com.financial.microservices.entity.Accounts;

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
