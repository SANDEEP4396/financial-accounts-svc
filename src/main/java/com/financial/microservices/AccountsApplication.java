package com.financial.microservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice API",
                version = "1.0",
                description = "APIs for managing accounts in the financial microservices application",
                contact = @Contact(
                        name = "Financial Service API Team",
                        email = "Test@gmail.com",
                        url = "https://www.financialservices.com/api"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        externalDocs = @ExternalDocumentation(
                description = "Accounts API Documentation",
                url = "https://www.financialservices.com/api/accounts/docs"
        )
)

public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
