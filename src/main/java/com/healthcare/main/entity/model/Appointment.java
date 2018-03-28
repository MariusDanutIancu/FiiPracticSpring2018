package com.healthcare.main.entity.model;

import javax.persistence.*;

@Entity
@Table(name="Appointment")
public class Appointment
{
    @Id
    @Column(name = "AppoinmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AppoinmentID;

    @Column(name = "Description", length = 100)
    private String Description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoctorID", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID", nullable = false)
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getAppoinmentID() {
        return AppoinmentID;
    }

    public void setAppoinmentID(Long appoinmentID) {
        AppoinmentID = appoinmentID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}

