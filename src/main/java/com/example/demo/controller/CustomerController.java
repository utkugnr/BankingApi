package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.request.CustomerCreateRequest;
import com.example.demo.request.CustomerUpdateRequest;
import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public Customer createCustomer(@RequestBody CustomerCreateRequest newCustomerRequest){
        return customerService.create(newCustomerRequest);
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{customerId}")
    public Customer getOneCustomer(@PathVariable Long customerId){
        return customerService.getOneCustomer(customerId);
    }
    @PutMapping("/{customerId}")
    public Customer updateOneCustomer(@PathVariable Long customerId , @RequestBody CustomerUpdateRequest updateCustomer){
        return customerService.update(customerId,updateCustomer);
    }
    @DeleteMapping("/{customerId}")
    public void deleteOneCustomer(@PathVariable Long customerId){
        customerService.delete(customerId);
    }
}
