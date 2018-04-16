package com.healthcare.main.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="patient")
@JsonIgnoreProperties(value = {"appointments"})
public class Patient extends Person
{
    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 150;

    @Column(name = "Age")
    @Digits(integer=3, fraction=0)
    @Min(MIN_AGE)
    @Max(MAX_AGE)
    private Integer Age;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
