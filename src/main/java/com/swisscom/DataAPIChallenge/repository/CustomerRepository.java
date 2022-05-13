package com.swisscom.DataAPIChallenge.repository;

import com.swisscom.DataAPIChallenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {}
