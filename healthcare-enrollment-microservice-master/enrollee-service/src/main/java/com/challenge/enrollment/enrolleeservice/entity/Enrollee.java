package com.challenge.enrollment.enrolleeservice.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/***
 * Person enrolled into the healthcare program. svc-enrollee
 */
@Entity
@Table(name = "enrollees")
public class Enrollee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollee_Id;
    private String name;
    private String activation_Status;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private String birth_Date;

    @Column(name = "phone_number")
    private String contact_Number;

    @OneToMany(mappedBy = "enrollee")
    @Column(nullable = true)
    @JsonManagedReference
    private List<Dependent> dependents = new ArrayList<>();

    public Enrollee() {
    }

    public Enrollee(String name) {
        this.name = name;
    }

    public Enrollee(String name, String activation_Status, String birth_Date, String contact_Number) {
        this.name = name;
        this.activation_Status = activation_Status;
        this.birth_Date = birth_Date;
        this.contact_Number = contact_Number;
    }

    public Enrollee(String name, String activation_Status, String birth_Date, String contact_Number,
            List<Dependent> dependents) {
        this.name = name;
        this.activation_Status = activation_Status;
        this.birth_Date = birth_Date;
        this.contact_Number = contact_Number;
        this.dependents = dependents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivation_Status() {
        return activation_Status;
    }

    public void setActivation_Status(String activation_Status) {
        this.activation_Status = activation_Status;
    }

    public String getBirth_Date() throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat outputFormat = new SimpleDateFormat("mm-dd-yyyy");

        Date date = inputFormat.parse(birth_Date);
        birth_Date = outputFormat.format(date);
        return birth_Date;
    }

    public void setBirth_Date(String birth_Date) {
        this.birth_Date = birth_Date;
    }

    public String getContact_Number() {
        return contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        this.contact_Number = contact_Number;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    public int getEnrollee_Id() {
        return enrollee_Id;
    }

    public void setEnrollee_Id(int enrollee_Id) {
        this.enrollee_Id = enrollee_Id;
    }

    public Enrollee(int enrollee_Id) {
        this.enrollee_Id = enrollee_Id;
    }

	

}
