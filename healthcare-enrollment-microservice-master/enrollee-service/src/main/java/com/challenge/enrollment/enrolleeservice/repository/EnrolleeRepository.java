package com.challenge.enrollment.enrolleeservice.repository;

import com.challenge.enrollment.enrolleeservice.entity.Enrollee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Integer>{
    public Enrollee findByName(String name);

}
