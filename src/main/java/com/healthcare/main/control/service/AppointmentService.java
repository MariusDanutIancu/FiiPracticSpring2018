package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;

import java.util.List;

public interface AppointmentService {

    Appointment getAppointment(Long id);
    List<Appointment> getAllAppointments();
    Appointment saveAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    void deleteAllAppointments();
    List<Appointment> findAllByDoctorAndPatient(Doctor doctor, Patient patient);
}
