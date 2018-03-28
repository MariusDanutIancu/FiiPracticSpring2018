package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/0.1/Appoinment")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping(value="/{id}")
    public Appointment getAppointment(@PathVariable("id") Long id) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment saveAppointment(@RequestBody Appointment appoinment)
    {
        return appointmentService.saveAppointment(appoinment);
    }

    @PostMapping(value="/appointments/{appointmentid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment putAppointment(@PathVariable("appointmentid") Long appointmentid, @RequestBody Appointment appointment)
            throws NotFoundException, BadRequestException
    {
        Doctor doctorDB = doctorService.getDoctor(appointment.getDoctorID());
        Patient patientDB = patientService.getPatient(appointment.getPatientID());
        Appointment appointmentDB = appointmentService.getAppointment(appointmentid);


        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointment.getDoctorID()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatientID()));
        }

        if(!appointmentid.equals(appointment.getAppointmentID())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        appointmentDB.setDoctor(doctorDB);
        appointmentDB.setPatient(patientDB);

        ObjectMapper.map2AppointmentDb(appointmentDB, appointment);

        return appointmentService.updateAppointment(appointmentDB);
    }

}
