package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    Appointment getAppointment(Long id);
    List<Appointment> getAppointments();
    List<Appointment> getAppointmentsByPatient(Patient patient);
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);
    List<Appointment> getAppointmentsDoctorAndPatient(Doctor doctor, Patient patient);
    Integer countAllBetweenStartTimeAndEndTimeAndDoctorOrPatient(Date startDate, Date endDate, Long doctorId, Long patientId);
    Appointment saveAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    void deleteAppointments();
}
