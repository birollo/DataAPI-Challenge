package com.swisscom.DataAPIChallenge.service;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public Customer getById(String customerId){
        if (customerRepository.findById(customerId).isPresent()){
            return customerRepository.findById(customerId).get();
        }else {
            return null;
        }
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }
}
