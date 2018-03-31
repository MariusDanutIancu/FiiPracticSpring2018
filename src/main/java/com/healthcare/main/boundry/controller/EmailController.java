package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/0.1/emails")
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping(value="/{id}")
    public Email getEmail(@PathVariable("id") Long id) throws NotFoundException
    {
        Email email = emailService.getEmail(id);
        if(email == null)
        {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return email;
    }

    /**
     *
     * @param email
     * @return
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Email saveEmail(@RequestBody Email email)
    {
        return emailService.saveEmail(email);
    }

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @GetMapping()
    public List<Email> getAllEmails() throws NotFoundException {
        List<Email> emailListDb = emailService.getAllEmails();
        if(emailListDb == null)
        {
            throw new NotFoundException("There are no emails in the database.");
        }
        return emailListDb;
    }

    /**
     *
     * @param id
     * @param email
     * @return
     * @throws BadRequestException
     * @throws NotFoundException
     */
    @PutMapping(value="/{id}")
    public Email updateEmail(@PathVariable("id") Long id, @RequestBody Email email) throws BadRequestException, NotFoundException
    {
        if(!id.equals(email.getEmailID()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Email emailDb = emailService.getEmail(id);
        if(emailDb == null){
            throw new NotFoundException(String.format("Email with id=%s was not found.", id));
        }

        ObjectMapper.map2EmailDb(emailDb, email);

        return emailService.updateEmail(emailDb);
    }

    /**
     *
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmail(@PathVariable Long id) throws NotFoundException
    {
        Email emailDb = emailService.getEmail(id);
        if(emailDb == null){
            throw new NotFoundException(String.format("Email with id=%s was not found.", id));
        }
        emailService.deleteEmail(emailDb);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllEmails()
    {
        emailService.deleteAllEmails();
    }
}
