package com.swisscom.DataAPIChallenge.repository;

import com.swisscom.DataAPIChallenge.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {}
