package com.example.Bank.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Open_Account")
public class OpenAccount {
    @Id
    private Long AccNo;

    private String accname;

    private double balance;

    @JsonIgnore
    @OneToMany(mappedBy = "account" ,cascade = CascadeType.ALL)
    private List<Statement> statements;

    public Long getAccNo() {
        return AccNo;
    }

    public void setAccNo(Long accNo) {
        AccNo = accNo;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
