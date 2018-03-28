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
}

