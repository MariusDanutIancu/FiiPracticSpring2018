package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Email;

public interface EmailService {

    Email getEmail(Long id);
    Email saveEmail(Email email);
}
