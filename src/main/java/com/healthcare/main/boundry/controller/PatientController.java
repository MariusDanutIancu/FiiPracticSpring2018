package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.PatientDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.PatientMapper;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.util.email.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api/0.1/patients")
public class PatientController
{
    private PatientService patientService;
    private EmailService emailService;

    //template used to build a specific email message
    private static final String PATIENT_EMAIL_MESSAGE_TEMPLATE =
            "You can see your appointment at http://localhost:8080/api/0.1/patients/%s";

    @Autowired
    public PatientController(PatientService patientService,  EmailService emailService)
    {
        this.patientService = patientService;
        this.emailService = emailService;
    }

    /**
     * Patient get request using doctor unique id.
     *
     * @param id patient unique id
     * @return requested patient
     * @throws NotFoundException no patient with requested id found in database
     */
    @GetMapping(value="/{id}")
    public PatientDto getPatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);
        if(patient == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }
        return PatientMapper.MAPPER.toPatientDto(patient);
    }

    /**
     * Patient get request
     *
     * @return all patients that are in the database
     * @throws NotFoundException no patients found in the database
     */
    @GetMapping
    public List<PatientDto> getPatients() throws NotFoundException {

        List<Patient> patientListDb = patientService.getAllPatients();
        if(patientListDb.size() == 0)
        {
            throw new NotFoundException("There are no emails in the database.");
        }
        return PatientMapper.MAPPER.toPatientsDto(patientListDb);
    }

    /**
     * Saves a patient in the database
     * @param patientDto patient data that needs to be saved
     * @return the saved patient
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PatientDto savePatient(@RequestBody PatientDto patientDto)
    {
        Patient patient = PatientMapper.MAPPER.toPatient(patientDto);
        patient = patientService.savePatient(patient);

        EmailUtil email = emailService.getEmail(patient, "Account created",
                String.format(PATIENT_EMAIL_MESSAGE_TEMPLATE, patient.getId()));
        emailService.sendEmailHttp(email);

        return PatientMapper.MAPPER.toPatientDto(patient);
    }

    /**
     * Saves a list of patients in the database
     *
     * @param id patient unique id
     * @param patient patient data that needs to be saved
     * @return null
     * @throws MethodNotAllowedException this method is not allowed
     */
    @PostMapping(value="/{id}")
    public Patient savePatient_not_allowed(@PathVariable("id") Long id, @RequestBody Patient patient) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    /**
     * Updates a patient in the database
     *
     * @param id patient unique id
     * @param patientDto patient data that needs to be saved
     * @return updated patient
     * @throws BadRequestException the ids from url and request body does not match
     * @throws NotFoundException patient not found
     */
    @PutMapping(value="/{id}")
    public PatientDto updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patientDto) throws BadRequestException, NotFoundException
    {
        if(!id.equals(patientDto.getPatient_id()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Patient patientDb = patientService.getPatient(id);
        if(patientDb == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }

        PatientMapper.MAPPER.toPatient(patientDto, patientDb);
        patientDb = patientService.updatePatient(patientDb);
        return PatientMapper.MAPPER.toPatientDto(patientDb);
    }

    /**
     * Delete a patient
     *
     * @param id patient unique id
     * @throws NotFoundException the requested patient was not found
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);
        if(patient == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }
        patientService.deletePatient(patient);
    }

    /**
     * Delete all doctors
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllPatients()
    {
        patientService.deleteAllPatients();
    }
}