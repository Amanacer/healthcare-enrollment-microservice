package com.challenge.enrollment.enrolleeservice.service;
import java.util.List;

import com.challenge.enrollment.enrolleeservice.entity.Enrollee;

import org.springframework.http.ResponseEntity;

public interface IEnrolleeService {
    Enrollee getEnrolleeByName(String name);
    ResponseEntity<List<Enrollee>> getAll();
    ResponseEntity<Enrollee> add(Enrollee enrollee);
    ResponseEntity<Enrollee> getById(int id);
    ResponseEntity<Enrollee> update(int id, Enrollee enrollee);
    ResponseEntity<Void> delete(int id);

}