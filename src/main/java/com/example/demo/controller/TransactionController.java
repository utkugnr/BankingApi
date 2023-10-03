package com.example.demo.controller;

import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.model.Transaction;
import com.example.demo.dto.request.TransferRequest;
import com.example.demo.dto.response.TransferResponse;
import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/transfer")
    public Transaction transferMoney(@RequestBody TransferRequest newTransferRequest) throws InsufficientResourcesException, InsufficientBalanceException {
        return transactionService.transferMoney(newTransferRequest);
    }
    @PostMapping("{accountId}/withdraw")
    public void withdrawMoney(@PathVariable Long accountId , @RequestParam BigDecimal transferAmount) throws InsufficientResourcesException {
        transactionService.withdraw(accountId,transferAmount);
    }
    @PostMapping("{accountId}/deposit")
    public void depositMoney(@PathVariable Long accountId , @RequestParam BigDecimal transferAmount){
        transactionService.deposit(accountId,transferAmount);
    }
    @PostMapping("/{accountId}/takeloan")
    public void takeLoan(@PathVariable Long accountId , @RequestParam BigDecimal transferAmount){
        transactionService.takeLoan(accountId,transferAmount);
    }
    @GetMapping("/history")
    public List<TransferResponse> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("/history/sender/{senderAccountId}")
    public List<TransferResponse> getAllTransfersBySenderAccountId(@PathVariable Long senderAccountId){
        return transactionService.getAllTransfersBySenderAccountId(senderAccountId);
    }
    @GetMapping("/history/receiver/{receiverAccountId}")
    public List<TransferResponse> getAllTransfersByReceiverAccountId(@PathVariable Long receiverAccountId){
        return transactionService.getAllTransfersByReceiverAccountId(receiverAccountId);
    }
    @GetMapping("/history/{transactionId}")
    public List<TransferResponse> getOneTransaction(@PathVariable Long transactionId){
        return transactionService.getOneTransaction(transactionId);
    }
}
