package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.entity.model.Email;
import com.healthcare.main.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/0.1/doctors")
public class DoctorController
{
    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;
    private EmailService emailService;

    @Autowired
    public DoctorController(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, EmailService emailService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.emailService = emailService;
    }

    @GetMapping(value="/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) throws NotFoundException
    {
        Doctor doctor = doctorService.getDoctor(id);
        if(doctor == null)
        {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return doctor;
    }

    @GetMapping
    public List<Doctor> getDoctors()
    {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Doctor saveDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.saveDoctor(doctor);
    }

    @PostMapping(value="/{id}")
    public Doctor saveDoctor_not_allowed(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PutMapping(value="/{id}")
    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws BadRequestException, NotFoundException
    {
        if(!id.equals(doctor.getDoctorID())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }

        ObjectMapper.map2DoctorDb(doctorDb, doctor);
        return  doctorService.updateDoctor(doctorDb);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) throws NotFoundException
    {
        Doctor doctorDb  = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        doctorService.deleteDoctor(doctorDb);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllDoctors()
    {
        doctorService.deleteAllDoctors();
    }

    @PostMapping(value="/{doctorid}/appointments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment saveAppointment(@PathVariable("doctorid") Long doctorid, @RequestBody Appointment appointment)
            throws NotFoundException, BadRequestException
    {
        Doctor doctorDB = doctorService.getDoctor(appointment.getDoctorID());
        Patient patientDB = patientService.getPatient(appointment.getPatientID());

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointment.getDoctorID()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatientID()));
        }

        if(!doctorid.equals(doctorDB.getDoctorID())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        appointment.setDoctor(doctorDB);
        appointment.setPatient(patientDB);

        Appointment appointmentDB = new Appointment();
        ObjectMapper.map2AppointmentDb(appointmentDB, appointment);

        return appointmentService.saveAppointment(appointmentDB);
    }

    @GetMapping(value="/{doctorid}/appointments/{patientid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment getAppointment(@PathVariable("doctorid") Long doctorID, @PathVariable("patientid") Long patientID) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PutMapping(value="/{doctorid}/emails")
    public Doctor addEmail(@PathVariable("doctorid") Long doctorid, @RequestBody Email email) throws BadRequestException, NotFoundException
    {
        Doctor doctorDb = doctorService.getDoctor(doctorid);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
        }

        Email emailDb = emailService.getEmail(email.getEmailID());
        if(emailDb == null){
            throw new NotFoundException(String.format("Email with id=%s was not found.", email.getEmailID()));
        }

        doctorDb.setEmail(emailDb);
        doctorDb = doctorService.updateDoctor(doctorDb);
        doctorDb.setEmailId(email.getEmailID());
        return doctorDb;
    }
}