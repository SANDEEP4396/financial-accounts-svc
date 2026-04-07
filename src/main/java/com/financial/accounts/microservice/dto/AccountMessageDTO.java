package com.financial.accounts.microservice.dto;

/**
 * DTO for account message details.
 *
 * @param accountNumber the account number associated with the message
 * @param name          the name of the account holder
 * @param email         the email address of the account holder
 * @param phoneNumber   the phone number of the account holder
 * @param message       the message content to be sent to the customer
 */
// Using Java record to create an immutable DTO for account message details,
// Advantages of using record:
// 1. Concise syntax: Records provide a compact syntax for defining data classes, reducing boilerplate code.
// 2. Immutability: Records are immutable by default, which can help prevent unintended side effects and improve thread safety.
// 3. Built-in methods: Records automatically generate methods like equals(), hashCode(), and toString(), which can save development time and reduce errors.
public record AccountMessageDTO(Long accountNumber, String name, String email, String phoneNumber, String message) {
}
