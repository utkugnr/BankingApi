package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.request.AccountCreateRequest;
import com.example.demo.request.AccountUpdateRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private CustomerService customerService;
    public AccountService(AccountRepository accountRepository, CustomerService customerService){
        this.accountRepository=accountRepository;
        this.customerService=customerService;
    }
    public Account create(AccountCreateRequest newAccountRequest) {
        Customer customer = customerService.getOneCustomer(newAccountRequest.getCustomerId());
        if (customer==null)
            return null;
        else {
            Account toSave = new Account();
            toSave.setAccountType(newAccountRequest.getAccountType());
            toSave.setAccountBalance(newAccountRequest.getAccountBalance());
            toSave.setCreationDate(newAccountRequest.getCreationDate());
            toSave.setCustomer(customer);
            return accountRepository.save(toSave);
        }
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Account getOneAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }
    public Account update(Long accountId, AccountUpdateRequest updateAccount) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()){
            Account foundAccount = account.get();
            foundAccount.setAccountType(updateAccount.getAccountType());
            foundAccount.setAccountBalance(updateAccount.getAccountBalance());
            foundAccount.setCreationDate(updateAccount.getCreationDate());
            accountRepository.save(foundAccount);
            return foundAccount;
        }
        else
            return null;
    }
    public void delete(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public List<Account> getAllAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Transactional
    public void withdraw(Long accountId, BigDecimal transferAmount) {
        Account account = getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();
        BigDecimal newBalance = currentBalance.subtract(transferAmount);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }
    @Transactional
    public void deposit(Long accountId, BigDecimal transferAmount){
        Account account = getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();

        BigDecimal newBalance = currentBalance.add(transferAmount);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }


}
