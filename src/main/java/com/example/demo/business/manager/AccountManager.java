package com.example.demo.business.manager;

import com.example.demo.business.service.AccountService;
import com.example.demo.entity.Account;
import com.example.demo.request.AccountCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountManager implements AccountService {
    @Override
    public Account create(AccountCreateRequest newAccountRequest) {
        return null;
    }

    @Override
    public Account getOneAccount(Long accountId) {
        return null;
    }

    @Override
    public Account update(Long accountId, Account newAccount) {
        return null;
    }

    @Override
    public void delete(Long accountId) {

    }
}
