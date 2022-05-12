package com.swisscom.DataAPIChallenge.service;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public Optional<Customer> getById(String customerId){
        return customerRepository.findById(customerId);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
