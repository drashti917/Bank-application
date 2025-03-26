package com.example.Bank.application.controller;

import com.example.Bank.application.model.OpenAccount;
import com.example.Bank.application.model.Statement;
import com.example.Bank.application.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService service;

    @PostMapping("open")
    public ResponseEntity<OpenAccount> createAc(@RequestBody OpenAccount account){
        OpenAccount acc= service.createAc(account);
        return new ResponseEntity<>(acc, HttpStatus.CREATED);
    }

    @PutMapping("withdraw")
    public  OpenAccount withdraw(@RequestParam long AccNo,@RequestParam double amount){
        return service.withdraw(AccNo,amount);
    }

    @PutMapping("deposit")
    public OpenAccount deposit(@RequestParam long AccNo, @RequestParam double amount){
        return service.deposit(AccNo, amount);
    }

    @PutMapping("transfer")
    public OpenAccount transfer(@RequestParam long firstAccNo,@RequestParam long secondAccno,@RequestParam double amount){
        return service.transfer(firstAccNo,secondAccno,amount);
    }

    @GetMapping("showBalance")
    public OpenAccount show(@RequestParam long AccNo){
        return service.show(AccNo);
    }

    @GetMapping("statements")
    @Transactional
    public List<Statement> getStatements(@RequestParam long AccNo) {
        return service.getAccountStatement(AccNo);
    }


}
