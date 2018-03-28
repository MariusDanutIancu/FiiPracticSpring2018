package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Email;

import java.util.List;

public interface EmailService {

    Email getEmail(Long id);
    Email saveEmail(Email email);
    List<Email> getAllEmails();
    Email updateEmail(Email email);
    void deleteEmail(Email email);
    void deleteAllEmails();
}
