package com.financial.accounts.microservice.service.client;

import com.financial.accounts.microservice.dto.cards.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// Eureka service will resolve the "cards" service name to the actual URL of the cards microservice,
// allowing this Feign client to communicate with it seamlessly.
@FeignClient(name = "cards", fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping(value = "api/fetch", consumes = "application/json")
    ResponseEntity<CardDTO> fetchCardDetails(@RequestParam String phoneNumber, @RequestHeader("X-Correlation-ID") String correlationId);
}
