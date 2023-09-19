package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.dto.request.CustomerCreateRequest;
import com.example.demo.dto.request.CustomerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer create(CustomerCreateRequest newCustomerRequest) {
        if (Objects.isNull(newCustomerRequest))
            return null;
        else{
            Customer toSave = new Customer();
            toSave.setCustomerName(newCustomerRequest.getCustomerName());
            toSave.setCustomerEmail(newCustomerRequest.getCustomerEmail());
            toSave.setCustomerPhone(newCustomerRequest.getCustomerPhone());
            toSave.setCustomerAddress(newCustomerRequest.getCustomerAddress());
            return customerRepository.save(toSave);
        }
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer getOneCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
    public Customer update(Long customerId, CustomerUpdateRequest updateCustomer) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            Customer foundCustomer = customer.get();
            foundCustomer.setCustomerName(updateCustomer.getCustomerName());
            foundCustomer.setCustomerEmail(updateCustomer.getCustomerEmail());
            foundCustomer.setCustomerPhone(updateCustomer.getCustomerPhone());
            foundCustomer.setCustomerAddress(updateCustomer.getCustomerAddress());
            customerRepository.save(foundCustomer);
            return foundCustomer;
        }
        else
            return null;
    }

    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
