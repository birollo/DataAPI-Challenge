package com.swisscom.DataAPIChallenge;

import com.swisscom.DataAPIChallenge.exception.DataAPIException;
import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import com.swisscom.DataAPIChallenge.repository.DialogRepository;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import com.swisscom.DataAPIChallenge.service.DialogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private DialogService dialogService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DialogRepository dialogRepository;

    final String customerId = "customerID";
    final String dialogId = "dialogId";

    @Test
    void customerServiceTest(){
        customerService.getById(customerId);
        Mockito.verify(customerRepository, Mockito.timeout(1)).findById(customerId);
    }

    @Test
    void dialogServiceTest(){
        dialogService.getById(dialogId);
        Mockito.verify(dialogRepository, Mockito.timeout(1)).findById(dialogId);
    }


}
