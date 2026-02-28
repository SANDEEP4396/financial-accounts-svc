package com.financial.accounts.microservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}

// If we make this a record class we will not be able to use @ConfigurationProperties to bind the properties from the application.yml file to this class.
// This is because record classes are immutable and do not have setters, which are required for @ConfigurationProperties to work.
// Therefore, we need to use a regular class with getters and setters to allow Spring Boot to bind the properties correctly.