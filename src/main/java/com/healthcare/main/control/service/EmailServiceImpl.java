package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Email;
import com.healthcare.main.entity.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email getEmail(Long id) {
        return null;
    }

    @Override
    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }
}
