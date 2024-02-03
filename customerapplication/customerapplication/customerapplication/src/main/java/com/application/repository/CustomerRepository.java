package com.application.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.application.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(String query, String query2, String query3);
    Page<Customer> findAll(Pageable pageable);
}