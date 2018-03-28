package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Email;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.control.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/0.1/patients")
public class PatientController
{
    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private EmailService emailService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService, EmailService emailService)
    {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.emailService = emailService;
    }

    @GetMapping(value="/{id}")
    public Patient getPatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);
        if(patient == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }
        return patientService.getPatient(id);
    }

    @GetMapping
    public List<Patient> getPatients()
    {
        return patientService.getAllPatients();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Patient savePatient(@RequestBody Patient patient)
    {
        patientService.savePatient(patient);
        return patient;
    }

    @PostMapping(value="/{id}")
    public Patient savePatient_not_allowed(@PathVariable("id") Long id, @RequestBody Patient patient) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PutMapping(value="/{id}")
    public Patient updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) throws BadRequestException, NotFoundException
    {
        if(!id.equals(patient.getPatientID()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Patient patientDb = patientService.getPatient(id);

        if(patientDb == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }

        ObjectMapper.map2PatientDb(patientDb, patient);
        return patientService.updatePatient(patientDb);
    }

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

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllPatients()
    {
        patientService.deleteAllPatients();
    }

    @PostMapping(value="/{patientid}/appointments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment saveAppointment(@PathVariable("patientid") Long patientid, @RequestBody Appointment appointment)
            throws NotFoundException, BadRequestException
    {
        Patient patientDB = patientService.getPatient(appointment.getPatientID());
        Doctor doctorDB = doctorService.getDoctor(appointment.getDoctorID());

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatientID()));
        }

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointment.getDoctorID()));
        }

        if(!patientid.equals(patientDB.getPatientID())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        appointment.setDoctor(doctorDB);
        appointment.setPatient(patientDB);

        Appointment appointmentDB = new Appointment();
        ObjectMapper.map2AppointmentDb(appointmentDB, appointment);

        return appointmentService.saveAppointment(appointmentDB);
    }

    @GetMapping(value="/{patientid}/appointments/{doctorid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment getAppointment(@PathVariable("patientid") Long patientID, @PathVariable("doctorid") Long doctorID) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PutMapping(value="/{paitentid}/emails")
    public Patient addEmail(@PathVariable("paitentid") Long paitentid, @RequestBody Email email) throws BadRequestException, NotFoundException
    {
        Patient patientDb = patientService.getPatient(paitentid);
        if(patientDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", paitentid));
        }

        Email emailDb = emailService.getEmail(email.getEmailID());
        if(emailDb == null){
            throw new NotFoundException(String.format("Email with id=%s was not found.", email.getEmailID()));
        }

        patientDb.setEmail(emailDb);
        patientDb = patientService.updatePatient(patientDb);
        patientDb.setEmailId(email.getEmailID());
        return patientDb;
    }
}