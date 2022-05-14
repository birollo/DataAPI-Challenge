package com.swisscom.DataAPIChallenge.service;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import com.swisscom.DataAPIChallenge.repository.DialogRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Seeder {

    final CustomerRepository customerRepository;
    final DialogRepository dialogRepository;

    public Seeder(CustomerRepository customerRepository, DialogRepository dialogRepository){
        this.customerRepository=customerRepository;
        this.dialogRepository=dialogRepository;
    }

    @PostConstruct
    private void postConstruct(){
        if (customerRepository.findAll().size()== 0){
            init();
        }
    }

    public void init(){
        List<Customer> customerList = new ArrayList<>();
        for (int k=0; k<5; k++) {
            Customer customer = new Customer();
            customer.setCustomerId("customer: " + k);
            List<Dialog> dialogList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Dialog dialog = new Dialog();
                dialog.setDialogId("Dialog:" + k+"."+i);
                dialog.setLanguage(Language.values()[new Random().nextInt(Language.values().length)]);
                dialog.setText("dialog text" + i);
                dialog.setDateTime(LocalDateTime.now());
                if(i % 2 == 0){
                    dialog.setConsent(true);
                } else {
                    dialog.setConsent(false);
                }
                dialog.setCustomer(customer);
                dialogList.add(dialog);
            }
            customer.setDialogList(dialogList);
            customerList.add(customer);
        }
        for (Customer c: customerList) {
            customerRepository.save(c);
        }
    }
}
