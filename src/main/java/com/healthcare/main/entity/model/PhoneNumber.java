package com.healthcare.main.entity.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="phone_number")
public class PhoneNumber
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="phone_number")
    @Pattern(regexp="(07)\\d{8}", message = "You did not enter a valid phone number")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
