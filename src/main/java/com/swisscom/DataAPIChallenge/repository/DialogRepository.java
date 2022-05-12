package com.swisscom.DataAPIChallenge.repository;

import com.swisscom.DataAPIChallenge.model.Customer;
import com.swisscom.DataAPIChallenge.model.Dialog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DialogRepository extends PagingAndSortingRepository<Dialog, String> {
    List<Dialog> getAllByLanguageOrderByDateTime(String language);

    List<Dialog> getAllByCustomerOrderByDateTime(Customer customer);
}
