package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.control.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@RestController
@RequestMapping(value = "/api/0.1/patients")
public class PatientController
{
    private PatientService patientService;
    private JavaMailSender javaMailSender;

    @Autowired
    public PatientController(PatientService patientService,  JavaMailSender javaMailSender)
    {
        this.patientService = patientService;
        this.javaMailSender = javaMailSender;
    }

//    /**
//     *
//     * @param id
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping(value="/{id}")
//    public Patient getPatient(@PathVariable("id") Long id) throws NotFoundException
//    {
//        Patient patient = patientService.getPatient(id);
//        if(patient == null)
//        {
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
//        }
//        return patientService.getPatient(id);
//    }
//
//    /**
//     *
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping
//    public List<Patient> getPatients() throws NotFoundException {
//
//        List<Patient> patientListDb = patientService.getAllPatients();
//        if(patientListDb.size() == 0)
//        {
//            throw new NotFoundException("There are no emails in the database.");
//        }
//        return patientListDb;
//    }
//
//    /**
//     *
//     * @param patient
//     * @return
//     */
//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Patient savePatient(@RequestBody Patient patient)
//    {
//        Patient patientDb = patientService.savePatient(patient);
//        sendEmail(patientDb);
//        return patientDb;
//    }
//
//    /**
//     *
//     * @param id
//     * @param patient
//     * @return
//     * @throws MethodNotAllowedException
//     */
//    @PostMapping(value="/{id}")
//    public Patient savePatient_not_allowed(@PathVariable("id") Long id, @RequestBody Patient patient) throws MethodNotAllowedException
//    {
//        throw new MethodNotAllowedException("Method is not allowed.");
//    }
//
//    /**
//     *
//     * @param id
//     * @param patient
//     * @return
//     * @throws BadRequestException
//     * @throws NotFoundException
//     */
//    @PutMapping(value="/{id}")
//    public Patient updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) throws BadRequestException, NotFoundException
//    {
//        if(!id.equals(patient.getPatientID()))
//        {
//            throw new BadRequestException("The id is not the same with id from object");
//        }
//
//        Patient patientDb = patientService.getPatient(id);
//
//        if(patientDb == null)
//        {
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
//        }
//
//        ObjectMapper.map2PatientDb(patientDb, patient);
//        return patientService.updatePatient(patientDb);
//    }
//
//    /**
//     *
//     * @param id
//     * @throws NotFoundException
//     */
//    @DeleteMapping(value="/{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deletePatient(@PathVariable("id") Long id) throws NotFoundException
//    {
//        Patient patient = patientService.getPatient(id);
//        if(patient == null){
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
//        }
//        patientService.deletePatient(patient);
//    }
//
//    /**
//     *
//     */
//    @DeleteMapping
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteAllPatients()
//    {
//        patientService.deleteAllPatients();
//    }
//
//    /**
//     *
//     * @param patient
//     */
//    private void sendEmail(Patient patient)
//    {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//
//        try {
//            mimeMessageHelper.setTo(patient.getEmail().getEmail());
//            mimeMessageHelper.setSubject("Account created");
//            mimeMessageHelper.setText(String.format("Patient name: %s", patient.getFirstName() + patient.getLastName()));
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}