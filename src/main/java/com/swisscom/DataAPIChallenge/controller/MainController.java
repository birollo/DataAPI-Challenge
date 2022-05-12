package com.swisscom.DataAPIChallenge.controller;


import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import com.swisscom.DataAPIChallenge.service.DialogService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MainController {

    private final DialogService dialogService;
    private final CustomerService customerService;

    public MainController(DialogService dialogService, CustomerService customerService){
        this.dialogService = dialogService;
        this.customerService = customerService;
    }

    @PostMapping("/data/{customerId}/{dialogId}")
    public ResponseEntity<String> dataAPIText(@PathVariable String customerId, @PathVariable String dialogId, @RequestBody Map<String, String> payload) {
        //featch JSON
        String text = payload.get("text");
        String language = payload.get("language");

        Customer customer;
        if(customerService.getById(customerId).isPresent()){
            customer = customerService.getById(customerId).get();
        }else {
            customer = new Customer();
            customer.setCustomerId(customerId);
        }

        if (dialogService.getById(dialogId).isPresent()){
            return ResponseEntity.badRequest().body("a dialogue with this ID ("+ dialogId + ") has already been saved");
        }
        Dialog dialog = new Dialog(dialogId,language, text, LocalDateTime.now(), false, customer );

        customer.getDialogList().add(dialog);

        customerService.save(customer);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/consent/{dialogId}")
    public void dataAPIConsent(@PathVariable String dialogId, @RequestBody String payload) {
        boolean consent = Boolean.parseBoolean(payload);
        if (dialogService.getById(dialogId).isPresent()){
            Dialog dialog = dialogService.getById(dialogId).get();
            if (consent){
                dialog.setConsent(true);
                dialogService.save(dialog);
            }else {
                dialogService.delete(dialog);
            }
        }
    }

    @GetMapping(value={"/data/{customerId}", "/data/?language={language}"})
    public List<String> getDataFromCustomer(@PathVariable(required = false) String customerId, @PathVariable(required = false) String language) throws Exception{
        List<Dialog> dialogList = new ArrayList<>();
        if (customerId != null){
            if (customerService.getById(customerId).isPresent()){
                Customer customer = customerService.getById(customerId).get();
                dialogList = dialogService.getAllByCustomerIdOrderByDateTime(customer);
            } else {
                throw new Exception("a customer with this ID ("+ customerId + ") has already been saved");
            }
        }

        if (language != null){
            dialogList = dialogService.getAllByLanguageOrderByDateTime(language);
        }

        List<String> dialogListWithConsent = new ArrayList<>();
        for (Dialog d : dialogList){
            if (d.isConsent()){
                dialogListWithConsent.add(d.toString());
            }
        }

        return dialogListWithConsent;
    }
}
