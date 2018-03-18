package com.healthcare.main.model;

public class Patient
{
    private Long PatientID;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Sex;
    private String PhoneNumber;

    public Patient(Long patientID, String firstName, String lastName, int age, String sex, String phoneNumber) {
        PatientID = patientID;
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Sex = sex;
        PhoneNumber = phoneNumber;
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
}
