package com.healthcare.main.model;


public class Doctor
{
    private Long DoctorID;
    private Long UserID;
    private String FirstName;
    private String LastName;
    private String Specialization;

    public Doctor() {}

    public Doctor(Long doctorID, Long userID, String firstName, String lastName, String specialization) {
        DoctorID = doctorID;
        UserID = userID;
        FirstName = firstName;
        LastName = lastName;
        Specialization = specialization;
    }

    public Long getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(Long doctorID) {
        DoctorID = doctorID;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
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
}
