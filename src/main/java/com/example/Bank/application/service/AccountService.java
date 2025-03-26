package com.example.Bank.application.service;

import com.example.Bank.application.model.OpenAccount;
import com.example.Bank.application.model.Statement;
import com.example.Bank.application.model.Transaction;
import com.example.Bank.application.repository.AccountRepository;
import com.example.Bank.application.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    StatementRepository statementRepository;

    private long generateAccNo() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.hashCode());
    }

    public OpenAccount createAc(OpenAccount account) {
        account.setAccNo(generateAccNo());
        return repository.save(account);
    }

    public OpenAccount withdraw (Long AccNo,double amount) {
        OpenAccount account = repository.findByAccount(AccNo).orElseThrow();

        if (account.getBalance() < amount) {
            throw new RuntimeException("not balance");
        }
        account.setBalance(account.getBalance() - amount);
        repository.save(account);

        Statement statement = new Statement();
        statement.setAccount(account);
        statement.setType(Transaction.WITHDRAW);
        statement.setAmount(amount);
        statement.setDate(new Date());
        statementRepository.save(statement);
        return account;
    }

    public OpenAccount deposit(Long AccNo, double amount) {
        OpenAccount account = repository.findByAccount(AccNo).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        repository.save(account);

        Statement statement =new Statement();
        statement.setAccount(account);
        statement.setType(Transaction.DEPOSIT);
        statement.setAmount(amount);
        statement.setDate(new Date());
        statementRepository.save(statement);
        return account;
    }

    public OpenAccount transfer(Long firstAccNo,Long secondAccno,double amount){
        OpenAccount firstaccount= repository.findByAccount(firstAccNo).orElseThrow(() -> new RuntimeException("account not found"));

        OpenAccount secondaccount=repository.findByAccount(secondAccno).orElseThrow(() -> new RuntimeException("account not found"));

        if(firstaccount.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        firstaccount.setBalance(firstaccount.getBalance()-amount);
        secondaccount.setBalance(secondaccount.getBalance()+amount);

        repository.save(firstaccount);
        repository.save(secondaccount);

        Statement statement =new Statement();
        statement.setAccount(firstaccount);
        statement.setType(Transaction.TRANSFER_OUT);
        statement.setAmount(amount);
        statement.setDate(new Date());
        statementRepository.save(statement);

        Statement statement2 =new Statement();
        statement2.setAccount(secondaccount);
        statement2.setType(Transaction.TRANSFER_IN);
        statement2.setAmount(amount);
        statement2.setDate(new Date());
        statementRepository.save(statement2);

        return secondaccount;
    }

    public OpenAccount show(Long AccNo){
        OpenAccount account = repository.findByAccount(AccNo).orElseThrow();
        return account;
    }

    public List<Statement> getAccountStatement(Long AccNo) {
        OpenAccount account = repository.findByAccount(AccNo) .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getStatements();
    }


}
