package com.healthcare.main.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
@JsonIgnoreProperties(value = {"appointments"})
public class Doctor extends Person
{
    private static final Integer FIELD_MIN_SIZE = 2;
    private static final Integer FIELD_MAX_SIZE = 20;

    @Column(name = "function")
    @Size(min = FIELD_MIN_SIZE, max = FIELD_MAX_SIZE, message = "Function must be between 2 and 20 characters")
    private String function;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
