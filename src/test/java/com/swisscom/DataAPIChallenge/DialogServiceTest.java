package com.swisscom.DataAPIChallenge;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
import com.swisscom.DataAPIChallenge.repository.CustomerRepository;
import com.swisscom.DataAPIChallenge.repository.DialogRepository;
import com.swisscom.DataAPIChallenge.service.CustomerService;
import com.swisscom.DataAPIChallenge.service.DialogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class DialogServiceTest {

    @InjectMocks
    private DialogService dialogService;

    @Mock
    private DialogRepository dialogRepository;

    final String customerId = "customerID";
    final String dialogId = "dialogId";
    Customer customer;
    Dialog dialog;


    @BeforeEach
    void init(){
        customer = new Customer(customerId, new ArrayList<>());
        dialog = new Dialog();
        dialog.setDialogId(dialogId);
        dialog.setLanguage(Language.EN);
        dialog.setText("Test text");
        dialog.setDateTime(LocalDateTime.now());
        dialog.setCustomer(customer);
    }

    @Test
    void saveCustomerTest(){
//        given(dialogRepository.save(dialog)).willReturn(dialog);
//        Dialog savedDialog = dialogService.save(dialog);
//        assertThat(savedDialog).isNotNull();

        dialogService.save(dialog);
        Mockito.verify(dialogRepository, Mockito.timeout(1)).save(dialog);
    }

    @Test
    void getCustomerByIdTest(){
//        given(dialogRepository.findById(dialogId)).willReturn(Optional.ofNullable(dialog));
//        Dialog savedDialog = dialogService.getById(dialogId);
//        assertThat(savedDialog).isNotNull();

        dialogService.getById(dialogId);
        Mockito.verify(dialogRepository, Mockito.timeout(1)).findById(dialogId);

    }



    @Test
    void deleteCustomer(){
        dialogService.delete(dialog);
        Mockito.verify(dialogRepository, Mockito.timeout(1)).delete(dialog);
    }



}
