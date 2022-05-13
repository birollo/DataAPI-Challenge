package com.swisscom.DataAPIChallenge.repository;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import com.swisscom.DataAPIChallenge.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogRepository extends JpaRepository<Dialog, String> {
    List<Dialog> getAllByLanguageOrderByDateTime(Language language);

    List<Dialog> getAllByCustomerOrderByDateTime(Customer customer);
}
