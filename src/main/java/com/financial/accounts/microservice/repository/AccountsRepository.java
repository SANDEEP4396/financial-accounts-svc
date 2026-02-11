package com.financial.accounts.microservice.repository;

import com.financial.accounts.microservice.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     *
     * /@Transactional annotation is used to ensure that the delete operation is executed within a transaction,
     * which is necessary for modifying queries in Spring Data JPA. If any error occurs during the execution of the delete operation,
     * the transaction will be rolled back to maintain data integrity.
     * /@Modifying annotation is used to indicate that the query is a modifying query,
     * which means it will perform an update or delete operation on the database.
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
