package com.healthcare.main.entity.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    private static final Integer NAME_MIN_SIZE = 2;
    private static final Integer NAME_MAX_SIZE = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PhoneNumber phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Email email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
