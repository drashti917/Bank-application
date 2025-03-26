package com.example.Bank.application.repository;

import com.example.Bank.application.model.OpenAccount;
import com.example.Bank.application.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer> {

}

