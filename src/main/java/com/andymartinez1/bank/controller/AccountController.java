package com.andymartinez1.bank.controller;

import com.andymartinez1.bank.dto.AccountDto;
import com.andymartinez1.bank.dto.TransactionDto;
import com.andymartinez1.bank.dto.TransferFundDto;
import com.andymartinez1.bank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // POST Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // GET Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // PUT Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    // PUT Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // GET All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // DELETE Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    // POST Transfer REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto) {
        accountService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Transfer Successful");
    }

    // GET Transactions REST API
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable("id") Long accountId) {
        List<TransactionDto> transactions = accountService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
