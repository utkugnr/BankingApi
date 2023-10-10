package com.example.demo.service;

import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.dto.request.TransferRequest;
import com.example.demo.dto.response.TransferResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.naming.InsufficientResourcesException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountService=accountService;
        this.accountRepository = accountRepository;
    }
    @Transactional
    public Transaction transferMoney(TransferRequest newTransferRequest) throws InsufficientResourcesException, InsufficientBalanceException {
        Account senderAccount = accountService.getOneAccount(newTransferRequest.getSenderAccountId());
        Account receiverAccount = accountService.getOneAccount(newTransferRequest.getReceiverAccountId());
        if(senderAccount.getAccountBalance().compareTo(newTransferRequest.getTransferAmount())<0)
            throw new InsufficientBalanceException("Insufficient funds to withdraw $");
        else{
            Transaction toSave = new Transaction();
            toSave.setTransferAmount(newTransferRequest.getTransferAmount());
            toSave.setTransactionType(newTransferRequest.getTransactionType());
            toSave.setTransactionTime(newTransferRequest.getTransactionTime());
            toSave.setSenderAccount(senderAccount);
            toSave.setReceiverAccount(receiverAccount);
            toSave = transactionRepository.save(toSave);

            withdraw(senderAccount.getId(), newTransferRequest.getTransferAmount());
            deposit(receiverAccount.getId(), newTransferRequest.getTransferAmount());
            return toSave;
        }
    }
    public void withdraw(Long accountId, BigDecimal transferAmount) throws InsufficientResourcesException {
        Account account = accountService.getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();
        if (currentBalance.compareTo(transferAmount) <0)
            throw new InsufficientResourcesException("Insufficient balance");
        else {
            BigDecimal newBalance = currentBalance.subtract(transferAmount);
            account.setAccountBalance(newBalance);
            accountRepository.save(account);
        }
    }
    public void deposit(Long accountId, BigDecimal transferAmount){
        Account account = accountService.getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();
        BigDecimal newBalance = currentBalance.add(transferAmount);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }
    public void takeLoan(Long accountId , BigDecimal transferAmount){
        Account account = accountService.getOneAccount(accountId);
        BigDecimal currentBalance = account.getAccountBalance();
        BigDecimal currentDebt = BigDecimal.valueOf(1000);
        BigDecimal interestRate = new BigDecimal("1.5");

        BigDecimal newBalance = currentBalance.add(transferAmount);
        BigDecimal newDebt = currentDebt.add(transferAmount.multiply(interestRate));
        account.setAccountBalance(newBalance);
        account.setAccountDebt(newDebt);

        Transaction toSave = new Transaction();
        toSave.setTransferAmount(transferAmount);
        toSave.setTransactionTime(LocalDateTime.now());
        toSave.setReceiverAccount(account);
        toSave = transactionRepository.save(toSave);

        accountRepository.save(account);
    }
    public List<TransferResponse> getAllTransactions(){
        List<Transaction> list;
        list = transactionRepository.findAll();
        return list.stream().map(TransferResponse::new).collect(Collectors.toList());
    }
    public List<TransferResponse> getOneTransaction(Long transactionId) {
        Optional<Transaction> list;
            list = transactionRepository.findById(transactionId);
            if (list.isPresent()) {
                return list.stream().map(TransferResponse::new).collect(Collectors.toList());
            }
            else
                return null;
    }
    public List<TransferResponse> getAllTransfersBySenderAccountId(Long accountId) {
        List<Transaction> list;
        list = transactionRepository.findBySenderAccountId(accountId);
        return list.stream().map(TransferResponse::new).collect(Collectors.toList());
    }
    public List<TransferResponse> getAllTransfersByReceiverAccountId(Long accountId) {
        List<Transaction> list;
        list = transactionRepository.findByReceiverAccountId(accountId);
        return list.stream().map(TransferResponse::new).collect(Collectors.toList());
    }
}
