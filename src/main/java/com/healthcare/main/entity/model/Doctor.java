package com.healthcare.main.entity.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="doctor")
public class Doctor extends Person
{
    @Column(name = "function")
    @Size(min = 2, max = 20, message = "Function must be between 2 and 20 characters")
    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
