package com.swisscom.DataAPIChallenge.service;


import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
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

    public Dialog getById(String dialogId){
        if (dialogRepository.findById(dialogId).isPresent()){
            return dialogRepository.findById(dialogId).get();
        }else {
            return null;
        }
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
    public List<Dialog> getAllByLanguageOrderByDateTime(Language language){
        return dialogRepository.getAllByLanguageOrderByDateTime(language);
    }
}
