package com.example.demo.business.manager;

import com.example.demo.business.service.CustomerService;
import com.example.demo.entity.Customer;
import com.example.demo.request.CustomerCreateRequest;

public class CustomerManager implements CustomerService {
    @Override
    public Customer create(CustomerCreateRequest newCustomerRequest) {
        return null;
    }

    @Override
    public Customer getOneCustomer(Long customerId) {
        return null;
    }

    @Override
    public Customer update(Long customerId, Customer newCustomer) {
        return null;
    }

    @Override
    public void delete(Long customerId) {

    }
}
