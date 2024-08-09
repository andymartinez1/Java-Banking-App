package com.andymartinez1.bank.repository;

import com.andymartinez1.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
