package com.healthcare.main.entity.model;


public class Doctor
{
    private Long DoctorID;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Sex;
    private String PhoneNumber;
    private String Specialization;

    public Doctor() {}

    public Doctor(Long doctorID, String firstName, String lastName, int age, String sex, String phoneNumber, String specialization) {
        DoctorID = doctorID;
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Sex = sex;
        PhoneNumber = phoneNumber;
        Specialization = specialization;
    }

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
}
