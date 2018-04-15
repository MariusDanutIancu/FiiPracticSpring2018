package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Person;
import com.healthcare.main.util.email.EmailUtil;

public interface EmailService {
    EmailUtil getEmail(Person person, String subject, String message);
    boolean validateEmail(EmailUtil email);
    void sendEmailText(EmailUtil email);
    void sendEmailHttp(EmailUtil email);
}
