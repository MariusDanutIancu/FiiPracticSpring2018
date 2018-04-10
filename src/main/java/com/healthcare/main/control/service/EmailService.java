package com.healthcare.main.control.service;

import com.healthcare.main.util.email.EmailUtil;

public interface EmailService {
    void sendEmailText(EmailUtil email);
    void sendEmailHttp(EmailUtil email);
}
