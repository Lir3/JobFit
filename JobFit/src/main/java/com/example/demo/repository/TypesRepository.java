package com.example.demo.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Types;

public interface TypesRepository extends JpaRepository<Types, BigInteger> {



    
}
