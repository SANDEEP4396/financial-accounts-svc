package com.financial.accounts.microservice.service.client;

import com.financial.accounts.microservice.dto.cards.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardDTO> fetchCardDetails(String phoneNumber, String correlationId) {
        return null;
    }
}
