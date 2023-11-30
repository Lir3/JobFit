	package com.example.demo.repository;
	
	import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;
	
	
	public interface AccountRepository extends JpaRepository<Account, BigInteger> {
	
		Account findByUserNameAndPassword(String username, String password);
	
		boolean existsByUserName(String username);
	
		Account findByUserName(String string);
	
		
	
	
	
	   
	}
