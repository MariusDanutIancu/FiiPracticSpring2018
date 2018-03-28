package com.healthcare.main.entity.model;

import javax.persistence.*;

@Entity
@Table(name="Email")
public class Email
{
    @Id
    @Column(name = "EmailID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmailID;

    @Column(name = "Email", length = 100)
    private String Email;

    @OneToOne(mappedBy = "email")
    private Doctor doctor;

    @OneToOne(mappedBy = "email")
    private Patient patient;

    public Long getEmailID() {
        return EmailID;
    }

    public void setEmailID(Long emailID) {
        EmailID = emailID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
