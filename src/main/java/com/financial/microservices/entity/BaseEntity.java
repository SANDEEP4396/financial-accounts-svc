package com.financial.microservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
// BaseEntity class to hold common fields for all entities. Acts as a parent class for other entities to inherit from.
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false) // Specifies that the updatedAt field should not be included in SQL INSERT statements. This means that when a new record is created, the updatedAt field will not be set and will typically be null until it is updated later.
    private LocalDateTime updatedAt;
    @Column(updatable = false) // Specifies that the createdBy field should not be updated after it is initially set. This means that once a value is assigned to createdBy, it cannot be changed.
    private String createdBy;
    private String updatedBy;
}
