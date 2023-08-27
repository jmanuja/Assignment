package com.alephlabs.assignment.repository;

import com.alephlabs.assignment.entity.RandomCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomCodesRepository extends JpaRepository<RandomCodes,Integer> {
}
