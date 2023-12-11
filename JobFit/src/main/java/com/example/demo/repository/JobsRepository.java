package com.example.demo.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, BigInteger> {
}
