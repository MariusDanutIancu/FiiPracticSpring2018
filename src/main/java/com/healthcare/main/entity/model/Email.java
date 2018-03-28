package com.healthcare.main.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Transient
    private Long DoctorID;

    @Transient
    private Long PatientID;

    public Long getDoctorID() {
        try
        {
            DoctorID = doctor.getDoctorID();
        }
        catch (NullPointerException e)
        {
            return null;
        }
        return DoctorID;
    }

    public void setDoctorID(Long doctorID) {
        DoctorID = doctorID;
    }

    public Long getPatientID() {
        try
        {
            PatientID=patient.getPatientID();
        }
        catch (NullPointerException e)
        {
            return null;
        }
        return PatientID;
    }

    public void setPatientID(Long patientID) {
        PatientID = patientID;
    }

    public Long getEmailID() {
        return EmailID;
    }

    public void setEmailID(Long emailID) {
        EmailID = emailID;
    }

    @JsonIgnore
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @JsonIgnore
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
