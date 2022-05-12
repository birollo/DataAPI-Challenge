package com.swisscom.DataAPIChallenge.service;


import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.repository.DialogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DialogService {

    private final DialogRepository dialogRepository;

    public DialogService(DialogRepository dialogRepository){
        this.dialogRepository = dialogRepository;
    }

    public Optional<Dialog> getById(String dialogId){
        return dialogRepository.findById(dialogId);
    }

    public Dialog save(Dialog dialog){
        return dialogRepository.save(dialog);
    }

    public void delete(Dialog dialog){
        dialogRepository.delete(dialog);
    }

    public List<Dialog> getAllByCustomerIdOrderByDateTime(Customer customer){
        return dialogRepository.getAllByCustomerOrderByDateTime(customer);
    }
    public List<Dialog> getAllByLanguageOrderByDateTime(String language){
        return dialogRepository.getAllByLanguageOrderByDateTime(language);
    }
}
