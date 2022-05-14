package com.swisscom.DataAPIChallenge;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    final String customerId = "customerID";
    Customer customer;

    @BeforeEach
    void init(){
        customer = new Customer(customerId, new ArrayList<>());
    }

    @Test
    void saveCustomerTest(){
//        given(customerRepository.save(customer)).willReturn(customer);
//        Customer savedCustomer = customerService.save(customer);
//        assertThat(savedCustomer).isNotNull();

        customerService.save(customer);
        Mockito.verify(customerRepository, Mockito.timeout(1)).save(customer);
    }

    @Test
    void getCustomerByIdTest(){
//        given(customerRepository.findById(customerId)).willReturn(Optional.ofNullable(customer));
//        Customer savedCustomer = customerService.getById(customerId);
//        assertThat(savedCustomer).isNotNull();

        customerService.getById(customerId);
        Mockito.verify(customerRepository, Mockito.timeout(1)).findById(customerId);

    }

    @Test
    void deleteCustomer(){
        customerService.delete(customer);
        Mockito.verify(customerRepository, Mockito.timeout(1)).delete(customer);
    }
}
