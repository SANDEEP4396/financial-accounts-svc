package com.financial.microservices.repository;

import com.financial.microservices.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

        boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

        Optional<Customer> findByPhoneNumber(String mobileNumber);
}
