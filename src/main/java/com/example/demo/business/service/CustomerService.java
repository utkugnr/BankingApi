package com.example.demo.business.service;

import com.example.demo.entity.Customer;
import com.example.demo.request.CustomerCreateRequest;

public interface CustomerService {
    Customer create(CustomerCreateRequest newCustomerRequest);
    Customer getOneCustomer(Long customerId);
    Customer update(Long customerId ,Customer newCustomer);
    void delete(Long customerId);
}
