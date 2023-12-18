package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Jobs;
import com.example.demo.entity.Types;

public interface TypesRepository extends JpaRepository<Types, BigInteger> {

	@Query("SELECT name FROM Types")
	List<String> findAllTypeNames();

	@Query("SELECT id FROM Types WHERE name = :typeName")
	Integer findTypeIdByName(String typeName);
	
	@Query("SELECT j FROM Jobs j WHERE j.type.id = :typeId")
    List<Jobs> findRecommendedJobsByTypeId(Integer typeId);



    
}
