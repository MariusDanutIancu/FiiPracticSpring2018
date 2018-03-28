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

import java.util.List;


@RestController
@RequestMapping(value="/api/0.1/appointments")
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
       return appointmentService.getAppointment(id);
    }

    @GetMapping()
    public List<Appointment> getAllAppointments()
    {
       return appointmentService.getAllAppointments();
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment postAppointment(@RequestBody Appointment appointment)
            throws NotFoundException
    {
        Doctor doctorDB = doctorService.getDoctor(appointment.getDoctorID());
        Patient patientDB = patientService.getPatient(appointment.getPatientID());

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointment.getDoctorID()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatientID()));
        }

        appointment.setDoctor(doctorDB);
        appointment.setPatient(patientDB);

        Appointment appointmentDB = new Appointment();
        ObjectMapper.map2AppointmentDb(appointmentDB, appointment);
        appointmentDB = appointmentService.saveAppointment(appointmentDB);
        appointmentDB.setDoctorID(appointment.getDoctorID());
        appointmentDB.setPatientID(appointment.getPatientID());

        return appointmentDB;
    }

    @PutMapping(value="/{id}")
    public Appointment updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appointment) throws BadRequestException, NotFoundException
    {
        if(!id.equals(appointment.getAppointmentID()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Appointment appointmentDb = appointmentService.getAppointment(id);
        if(appointmentDb == null){
            throw new NotFoundException(String.format("Appointment with id=%s was not found.", id));
        }

        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }

        Patient patientDB = patientService.getPatient(appointment.getPatientID());
        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatientID()));
        }

        appointment.setDoctor(doctorDb);
        appointment.setPatient(patientDB);

        ObjectMapper.map2AppointmentDb(appointmentDb, appointment);

        return appointmentService.updateAppointment(appointmentDb);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) throws NotFoundException
    {
        Appointment appointmentDb = appointmentService.getAppointment(id);
        if(appointmentDb == null){
            throw new NotFoundException(String.format("Appointment with id=%s was not found.", id));
        }
        appointmentService.deleteAppoinment(appointmentDb);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllAppoinments()
    {
        appointmentService.deleteAllAppoinments();
    }

    @GetMapping(value="/filter")
    public List<Appointment> findAllByDoctorAndPatient(@RequestParam("patientid") Long patientid, @RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Doctor doctorDb = doctorService.getDoctor(doctorid);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
        }

        Patient patientDB = patientService.getPatient(patientid);
        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", patientid));
        }

        return appointmentService.findAllByDoctorAndPatient(doctorDb, patientDB);
    }
}
