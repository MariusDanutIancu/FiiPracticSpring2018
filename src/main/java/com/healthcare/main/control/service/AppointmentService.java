package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment getAppointment(Long id);
    List<Appointment> getAllAppointments();
    Appointment saveAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    void deleteAppoinment(Appointment appointment);
    void deleteAllAppoinments();
}
