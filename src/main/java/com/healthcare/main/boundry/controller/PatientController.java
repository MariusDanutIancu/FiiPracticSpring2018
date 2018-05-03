package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.PatientDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.PatientMapper;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.control.service.MessageService;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.properties.CustomProperties;
import com.healthcare.main.common.EmailCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value = "/api/0.1/patients")
public class PatientController
{
    private PatientService patientService;
    private EmailService emailService;
    private MessageService messageService;
    private CustomProperties customProps;

    @Autowired
    public PatientController(PatientService patientService,  EmailService emailService, MessageService messageService,
                             CustomProperties customProps)
    {
        this.patientService = patientService;
        this.emailService = emailService;
        this.messageService = messageService;
        this.customProps = customProps;
    }

    /**
     * Patient get request using doctor unique id.
     *
     * @param id patient unique id
     * @return requested patient
     * @throws NotFoundException no patient with requested id found in database
     */
    @GetMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PatientDto getPatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patientEntity = patientService.getPatient(id);
        if(patientEntity == null)
        {
            throw new NotFoundException(String.format(messageService.getMessage("patient.not.found.id"), id));
        }
        return PatientMapper.MAPPER.toPatientDto(patientEntity);
    }

    /**
     * Patient get request
     *
     * @return all patients that are in the database
     * @throws NotFoundException no patients found in the database
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PatientDto> getPatients() throws NotFoundException {

        List<Patient> patientListEntity = patientService.getAllPatients();
        if(patientListEntity.isEmpty())
        {
            throw new NotFoundException("There are no patients in the database.");
        }
        return PatientMapper.MAPPER.toPatientsDto(patientListEntity);
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

        String message = String.format(messageService.getMessage("account.created.patient.link"),
                customProps.getPatientsurl()) + patient.getId();

        emailService.sendEmailHttp(emailService.getEmail(patient, "Account created",
                String.format(message, patient.getId())));

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
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
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
    @ResponseStatus(value = HttpStatus.OK)
    public PatientDto updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patientDto) throws BadRequestException, NotFoundException
    {
        if(!id.equals(patientDto.getPatient_id()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Patient patientEntity = patientService.getPatient(id);
        if(patientEntity == null)
        {
            throw new NotFoundException(String.format(messageService.getMessage("patient.not.found.id"), id));
        }

        PatientMapper.MAPPER.toPatient(patientDto, patientEntity);
        return PatientMapper.MAPPER.toPatientDto(patientService.updatePatient(patientEntity));
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
        Patient patientEntity = patientService.getPatient(id);
        if(patientEntity == null){
            throw new NotFoundException(String.format(messageService.getMessage("patient.not.found.id"), id));
        }
        patientService.deletePatient(patientEntity);
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