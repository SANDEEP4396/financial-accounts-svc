package com.financial.accounts.microservice.repository;

import com.financial.accounts.microservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

        boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

        Optional<Customer> findByPhoneNumber(String mobileNumber);
}
