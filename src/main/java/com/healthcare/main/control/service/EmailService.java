package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Person;
import com.healthcare.main.common.EmailCommon;

public interface EmailService {

    /**
     *
     * @param person
     * @param subject
     * @param message
     * @return
     */
    EmailCommon getEmail(Person person, String subject, String message);

    /**
     *
     * @param email
     * @return
     */
    boolean validateEmail(EmailCommon email);

    /**
     *
     * @param email
     */
    void sendEmailText(EmailCommon email);

    /**
     *
     * @param email
     */
    void sendEmailHttp(EmailCommon email);
}
