package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.entity.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/0.1/Appoinment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(value="/{id}")
    public Appointment getEmail(@PathVariable("id") Long id) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment saveEmail(@RequestBody Appointment appoinment)
    {
        return appointmentService.saveAppointment(appoinment);
    }
}
