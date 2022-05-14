package com.swisscom.DataAPIChallenge.controller;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import com.swisscom.DataAPIChallenge.service.DialogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final DialogService dialogService;
    private final CustomerService customerService;
    private final DataAPIExceptionHandler dataAPIExceptionHandler;

    public MainController(DialogService dialogService, CustomerService customerService, DataAPIExceptionHandler dataAPIExceptionHandler){
        this.dialogService = dialogService;
        this.customerService = customerService;
        this.dataAPIExceptionHandler = dataAPIExceptionHandler;
    }

    @PostMapping("/data/{customerId}/{dialogId}")
    public ResponseEntity<Object> dataAPIText(@PathVariable String customerId, @PathVariable String dialogId, @RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        Language language = Language.valueOf(payload.get("language"));

        Customer customer;
        if(customerService.getById(customerId) != null){
            customer = customerService.getById(customerId);
        }else {
            customer = new Customer();
            customer.setCustomerId(customerId);
        }

        if (dialogService.getById(dialogId) != null){
            return ResponseEntity.badRequest().body("a dialogue with this ID ("+ dialogId + ") has already been saved");
        }
        Dialog dialog = new Dialog(dialogId,language, text, LocalDateTime.now(), false, customer );

        customer.getDialogList().add(dialog);

        customerService.save(customer);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/consent/{dialogId}")
    public ResponseEntity<Object> dataAPIConsent(@PathVariable String dialogId, @RequestBody String payload) {
        boolean consent = Boolean.parseBoolean(payload);
        if (dialogService.getById(dialogId) != null){
            Dialog dialog = dialogService.getById(dialogId);
            if (consent){
                dialog.setConsent(true);
                dialogService.save(dialog);
            }else {
                Customer c = customerService.getById(dialog.getCustomer().getCustomerId());
                customerService.delete(c);
            }
            return ResponseEntity.ok().build();
        }else {
            return dataAPIExceptionHandler.handlerException(HttpStatus.NOT_FOUND, "Impossible to find a dialog with id=" +dialogId);
        }
    }

    @GetMapping(value={"/data/{customerId}", "/data/?language={language}"})
    public ResponseEntity<Object> getDataFromCustomer(@PathVariable(required = false) String customerId, @PathVariable(required = false) String language){
        Customer customer;
        List<Dialog> dialogList;
        if (customerService.getById(customerId) != null){
            customer = customerService.getById(customerId);
            dialogList= dialogService.getAllByCustomerIdOrderByDateTime(customer);
        } else if (language != null){
            dialogList = dialogService.getAllByLanguageOrderByDateTime( Language.valueOf(language));
        } else {
            return dataAPIExceptionHandler.handlerException(HttpStatus.NOT_FOUND, "Impossible to find a customer with id=" +customerId);
        }
        List<String> dialogListWithConsent = new ArrayList<>();
        for (Dialog d : dialogList){
            if (d.isConsent()){
                dialogListWithConsent.add(d.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(dialogListWithConsent);
    }
}
