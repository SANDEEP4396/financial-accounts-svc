package com.financial.accounts.microservice.service.client;

import com.financial.accounts.microservice.dto.loans.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Eureka service will resolve the "cards" service name to the actual URL of the cards microservice,
// allowing this Feign client to communicate with it seamlessly.
@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "api/fetchLoan", consumes = "application/json")
    ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam String phoneNumber);
}
