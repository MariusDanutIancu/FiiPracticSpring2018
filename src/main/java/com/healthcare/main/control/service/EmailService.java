package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Person;
import com.healthcare.main.util.email.EmailUtil;

public interface EmailService {

    /**
     *
     * @param person
     * @param subject
     * @param message
     * @return
     */
    EmailUtil getEmail(Person person, String subject, String message);

    /**
     *
     * @param email
     * @return
     */
    boolean validateEmail(EmailUtil email);

    /**
     *
     * @param email
     */
    void sendEmailText(EmailUtil email);

    /**
     *
     * @param email
     */
    void sendEmailHttp(EmailUtil email);
}
