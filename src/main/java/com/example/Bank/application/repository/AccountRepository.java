package com.example.Bank.application.repository;

import com.example.Bank.application.model.OpenAccount;
import com.example.Bank.application.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<OpenAccount,Long> {
    @Query("SELECT a FROM OpenAccount a WHERE a.AccNo= :AccNo")
    Optional<OpenAccount> findByAccount(@Param("AccNo") Long AccNo);
}
