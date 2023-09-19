package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.dto.request.AccountCreateRequest;
import com.example.demo.dto.request.AccountUpdateRequest;
import com.example.demo.dto.response.AccountResponse;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<AccountResponse> getAllAccounts(){
        List<Account> list;
        list = accountRepository.findAll();
        return list.stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());
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

    public List<AccountResponse> getAllAccountsByCustomerId(Long customerId) {
        List<Account> list;
        list = accountRepository.findByCustomerId(customerId);
        return list.stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());
    }

    @Transactional
    public void withdraw(Long accountId, BigDecimal transferAmount) throws InsufficientResourcesException {
        Account account = getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();
        if (currentBalance.compareTo(transferAmount)<0)
            throw new InsufficientResourcesException("Insufficient balance");
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
