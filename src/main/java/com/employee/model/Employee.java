package com.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    private Double ctc;

    private String organisation;

    // Getters and setters
}