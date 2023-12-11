package com.example.demo.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, BigInteger> {


	Optional<Question> findById(Question question);


}