package com.example.demo.business.service;

import com.example.demo.entity.Account;
import com.example.demo.request.AccountCreateRequest;

public interface AccountService {
    Account create(AccountCreateRequest newAccountRequest);
    Account getOneAccount(Long accountId);
    Account update(Long accountId , Account newAccount);
    void delete(Long accountId);
}
