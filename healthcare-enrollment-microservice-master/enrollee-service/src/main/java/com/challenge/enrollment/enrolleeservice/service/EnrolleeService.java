package com.challenge.enrollment.enrolleeservice.service;


import java.util.List;
import java.util.Optional;

import com.challenge.enrollment.enrolleeservice.entity.Dependent;
import com.challenge.enrollment.enrolleeservice.entity.Enrollee;
import com.challenge.enrollment.enrolleeservice.repository.DependentRepository;
import com.challenge.enrollment.enrolleeservice.repository.EnrolleeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
// Service Implementation
public class EnrolleeService implements IEnrolleeService {

    @Autowired
    private EnrolleeRepository enrolleeRepository;
    @Autowired
    private DependentRepository dependentRepository;

    @Override
    public Enrollee getEnrolleeByName(String name) {
        return enrolleeRepository.findByName(name);
    }

    @Override
    public ResponseEntity<List<Enrollee>> getAll() {
        List<Enrollee> enrolleeList = enrolleeRepository.findAll();
        if (enrolleeList.isEmpty()) {
            System.out.println("No enrollees found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(enrolleeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Enrollee> add(Enrollee enrollee) {
        Enrollee savedEnrollee;
        try {
            savedEnrollee = enrolleeRepository.save(enrollee);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedEnrollee, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Enrollee> getById(int id) {

        Optional<Enrollee> enrollee = enrolleeRepository.findById(Integer.valueOf(id));
        return enrollee.isPresent() ? new ResponseEntity<>(enrollee.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Enrollee> update(int id, Enrollee enrollee) {
        
        
        Optional<Enrollee> isEnrolleePresent = enrolleeRepository.findById(id);
        if (!isEnrolleePresent.isPresent()) {
            System.out.println("Could not find enrollee in system");
            return ResponseEntity.notFound().build(); //ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        enrollee.setEnrollee_Id(id);
    
        Enrollee updatedEnrollee = enrolleeRepository.save(enrollee);
        return new ResponseEntity<>(updatedEnrollee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        try {
            enrolleeRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Dependent> addDependent(int enrolleeId, Dependent dependent) {
        Enrollee enrolleeToGet;
        try {
            enrolleeToGet = enrolleeRepository.getOne(Integer.valueOf(enrolleeId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dependent.setEnrollee(enrolleeToGet);
        dependentRepository.save(dependent);
        return new ResponseEntity<>(dependent, HttpStatus.CREATED);
    }


    public ResponseEntity<Void> deleteDependentByEnrolleeId(int enrolleeId, int dependentId) 
    {
        Enrollee enrolleeToFind;
        Dependent dependentToDelete;
            try{
                enrolleeToFind = enrolleeRepository.getOne(Integer.valueOf(enrolleeId));
                dependentToDelete = dependentRepository.getOne(Integer.valueOf(dependentId));

                if(enrolleeToFind.equals(dependentToDelete.getEnrollee())){
                    dependentRepository.deleteById(dependentId);   
            }
        } 
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);       
    }

    public ResponseEntity<Dependent> updateDependent(int enrolleeId, int dependentId, Dependent dependent) {
        Enrollee isEnrolleePresent = enrolleeRepository.getOne(enrolleeId);
        Optional<Dependent> isDependentPresent = dependentRepository.findById(dependentId);
            try{    
            if (isDependentPresent.isPresent()) {
                dependent.setEnrollee(isEnrolleePresent);
                dependentRepository.save(dependent);
                
            }
        }  catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }   
            return new ResponseEntity<>(dependent, HttpStatus.OK);
        }
}