package com.example.Bank.application.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "AccNo")
    private OpenAccount account;

    @Enumerated(EnumType.STRING)
    private Transaction type;

    private double amount;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OpenAccount getAccount() {
        return account;
    }

    public void setAccount(OpenAccount account) {
        this.account = account;
    }

    public Transaction getType() {
        return type;
    }

    public void setType(Transaction type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
