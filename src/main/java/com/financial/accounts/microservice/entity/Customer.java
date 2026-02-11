package com.financial.accounts.microservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Optional if the field name matches the column name in the database.
    // This annotation is used to specify the column name in the database that this field maps to.
    // If the field name and column name are the same, this annotation can be omitted.
    @Column(name = "customer_id")
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
