package com.financial.accounts.microservice.service.client;

import com.financial.accounts.microservice.dto.loans.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{

    @Override
    public ResponseEntity<LoanDTO> fetchLoanDetails(String phoneNumber, String correlationId) {
        return null;
    }
}
