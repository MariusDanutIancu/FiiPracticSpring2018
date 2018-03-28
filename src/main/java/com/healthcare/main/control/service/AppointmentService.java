package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;

public interface AppointmentService {

    Appointment getAppointment(Long id);
    Appointment saveAppointment(Appointment doctor);
    Appointment updateAppointment(Appointment appointment);
}
