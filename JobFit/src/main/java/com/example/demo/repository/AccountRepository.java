package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUserNameAndPassword(String username, String password);

	boolean existsByUserName(String username);

	Account findByUserName(String string);

	



   
}
