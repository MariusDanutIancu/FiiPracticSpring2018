package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/0.1/emails")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value="/{id}")
    public Email getEmail(@PathVariable("id") Long id) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Email saveEmail(@RequestBody Email email)
    {
        return emailService.saveEmail(email);
    }
}
