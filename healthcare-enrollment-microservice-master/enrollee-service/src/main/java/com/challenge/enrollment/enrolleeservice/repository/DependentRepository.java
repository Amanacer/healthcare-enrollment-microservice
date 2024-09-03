package com.challenge.enrollment.enrolleeservice.repository;

import com.challenge.enrollment.enrolleeservice.entity.Dependent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends JpaRepository <Dependent,Integer> {
     
}
