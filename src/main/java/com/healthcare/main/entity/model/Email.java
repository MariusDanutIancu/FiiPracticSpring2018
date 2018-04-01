package com.healthcare.main.entity.model;

import javax.persistence.*;

@Entity
@Table(name="Email")
public class Email
{
    @Id
    @Column(name = "email_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmailID;

    @Column(name = "Email", length = 100)
    private String Email;

    public Long getEmailID() {
        return EmailID;
    }

    public void setEmailID(Long emailID) {
        EmailID = emailID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
