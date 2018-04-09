package com.healthcare.main.entity.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="patient")
public class Patient extends Person
{
    @Column(name = "Age")
    @Digits(integer=3, fraction=0)
    @Min(1)
    @Max(150)
    private int Age;

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
