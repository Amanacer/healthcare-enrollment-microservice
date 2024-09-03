package com.challenge.enrollment.enrolleeservice.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A dependent of an Enrollee.
 * An Enrollee's health plan’s spouse and eligible child or other member who
 * meets the applicable eligibility requirements of a group benefits agreement 
 *
 */
@Entity
@Table(name = "dependents")
public class Dependent implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int dependent_Id;
    private String name;
    private String birth_Date;

    @ManyToOne
    @JoinColumn(name = "enrollee_id")
    @JsonBackReference
    private Enrollee enrollee;

    public Dependent() {
    }

    public Dependent(String name, String birth_Date) {
        this.name = name;
        this.birth_Date = birth_Date;
    }

    public Dependent(int dependent_Id, String name, String birth_Date, Enrollee enrollee) {
        this.dependent_Id = dependent_Id;
        this.name = name;
        this.birth_Date = birth_Date;
        this.enrollee = enrollee;
    }

    public Dependent(String name, String birth_Date, Enrollee enrollee) {
        this.name = name;
        this.birth_Date = birth_Date;
        this.enrollee = enrollee;
    }

    @Override
    public String toString() {
        return "Dependent [birth_Date=" + birth_Date + ", dependent_Id=" + dependent_Id + ", enrollee=" + enrollee
                + ", name=" + name + "]";
    }

    public int getDependent_Id() {
        return dependent_Id;
    }

    public void setDependent_Id(int dependent_Id) {
        this.dependent_Id = dependent_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_Date() throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat outputFormat = new SimpleDateFormat("mm-dd-yyyy");
        Date date = inputFormat.parse(birth_Date);
        birth_Date = outputFormat.format(date);
        return birth_Date;
    }

    //
    public void setBirth_Date(String birth_Date) throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("mm-dd-yyyy");
        DateFormat outputFormat = new  SimpleDateFormat("yyyy-mm-dd");
        Date date = inputFormat.parse(birth_Date);
        birth_Date = outputFormat.format(date);
        this.birth_Date = birth_Date;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrolleeToGet) {
        this.enrollee = enrolleeToGet;
    }
}
