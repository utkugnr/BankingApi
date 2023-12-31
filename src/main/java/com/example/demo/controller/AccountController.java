package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.dto.request.AccountCreateRequest;
import com.example.demo.dto.request.AccountUpdateRequest;
import com.example.demo.dto.response.AccountResponse;
import com.example.demo.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public Account createAccount(@RequestBody AccountCreateRequest newAccountRequest){
        return accountService.create(newAccountRequest);
    }
    @GetMapping
    public List<AccountResponse> getAllAccounts(){
        return accountService.getAllAccounts();
    }
    @GetMapping("/customers/{customerId}")
    public List<AccountResponse> getAccountsByCustomerId(@PathVariable Long customerId){
        return accountService.getAllAccountsByCustomerId(customerId);
    }
    @GetMapping("/{accountId}")
    public Account getOneAccount(@PathVariable Long accountId){
        return accountService.getOneAccount(accountId);
    }
    @PutMapping("/{accountId}")
    public Account updateOneAccount(@PathVariable Long accountId , @RequestBody AccountUpdateRequest updateAccount){
        return accountService.update(accountId,updateAccount);
    }
    @DeleteMapping("/{accountId}")
    public void deleteOneAccount(@PathVariable Long accountId){
        accountService.delete(accountId);
    }
}
