package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Email;
import com.healthcare.main.entity.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email getEmail(Long id) {
       return emailRepository.findById(id).orElse(null);
    }

    @Override
    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

    @Override
    public List<Email> getAllEmails(Long id) {
        return emailRepository.findAll();
    }

    @Override
    public Email updateEmail(Email email) {
        return emailRepository.save(email);
    }

    @Override
    public void deleteEmail(Email email) {
        emailRepository.delete(email);
    }

    @Override
    public void deleteAllEmails() {
        emailRepository.deleteAll();
    }
}
