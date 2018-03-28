package com.healthcare.main.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Doctor")
public class Doctor
{
    @Id
    @Column(name = "DoctorID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DoctorID;

    @Column(name = "FirstName", length = 20)
    private String FirstName;

    @Column(name = "LastName", length = 20)
    private String LastName;

    @Column(name = "Age")
    private int Age;

    @Column(name = "Sex")
    private String Sex;

    @Column(name = "PhoneNumber")
    private String PhoneNumber;

    @Column(name = "Specialization")
    private String Specialization;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "EmailID")
    private Email email;

    @Transient
    private Long emailId;

    public Long getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(Long doctorID) {
        DoctorID = doctorID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Set<Appointment> getAppointments() {

        for(Appointment ap:this.appointments)
        {
            ap.setPatientID(ap.getPatient().getPatientID());
            ap.setDoctorID(ap.getDoctor().getDoctorID());
        }

        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    @JsonIgnore
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }
}
