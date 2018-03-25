package com.healthcare.main.entity.model;

public class Patient
{
    private Long PatientID;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Sex;
    private String PhoneNumber;
    private String malady;
    private String medicalTreatment;

    public Patient(){}

    public Patient(Long patientID, String firstName, String lastName, int age, String sex, String phoneNumber, String malady, String medicalTreatment) {
        PatientID = patientID;
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Sex = sex;
        PhoneNumber = phoneNumber;
        this.malady = malady;
        this.medicalTreatment = medicalTreatment;
    }

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
        return malady;
    }

    public void setMalady(String malady) {
        this.malady = malady;
    }

    public String getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }
}
