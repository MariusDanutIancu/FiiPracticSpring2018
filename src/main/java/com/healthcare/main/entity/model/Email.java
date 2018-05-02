package com.healthcare.main.entity.model;

import com.healthcare.main.validators.EmailAnnotation;

import javax.persistence.*;

@Entity
@Table(name="Email")
public class Email
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @EmailAnnotation
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
