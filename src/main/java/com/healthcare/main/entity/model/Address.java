package com.healthcare.main.entity.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="address")
public class Address
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="street")
    @Size(min = 2, max = 20, message = "Street must be between 2 and 20 characters")
    private String street;

    @Column(name="county")
    @Size(min = 2, max = 20, message = "County must be between 2 and 20 characters")
    private String county;

    @Column(name="city")
    @Size(min = 2, max = 20, message = "City must be between 2 and 20 characters")
    private String city;

    @Column(name="state")
    @Size(min = 2, max = 20, message = "State must be between 2 and 20 characters")
    private String state;

    @Column(name="postal_code")
    @Size(min = 2, max = 20, message = "Postal_code must be between 2 and 1O characters")
    private String postal_code;

    @Column(name="country")
    @Size(min = 2, max = 20, message = "Country must be between 2 and 20 characters")
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
