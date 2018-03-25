package com.healthcare.main.entity.model;

import javax.persistence.*;


@Entity
@Table(name="Patient")
public class Patient
{
    @Id
    @Column(name="PatientID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PatientID;

    @Column(name = "FirstName", length = 40)
    private String FirstName;

    @Column(name = "LastName", length = 40)
    private String LastName;

    @Column(name = "Age")
    private int Age;

    @Column(name = "Sex")
    private String Sex;

    @Column(name = "PhoneNumber")
    private String PhoneNumber;

    @Column(name = "Malady")
    private String Malady;

    @Column(name = "MedicalTreatment")
    private String MedicalTreatment;

    public Long getPatientID() {
        return PatientID;
    }

    public void setPatientID(Long patientID) {
        PatientID = patientID;
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

    public String getMalady() {
        return Malady;
    }

    public void setMalady(String malady) {
        this.Malady = malady;
    }

    public String getMedicalTreatment() {
        return MedicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.MedicalTreatment = medicalTreatment;
    }
}