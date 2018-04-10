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
    List<Appointment> findAllByDoctorAndEndTimeGreaterThan(Doctor doctor, Date end_date);
    List<Appointment> findAllByEndTimeGreaterThan(Date end_date);
    List<Appointment> findAllByTookPlace(boolean took_place);
    List<Appointment> findAllByEndTimeLessThanEqual(Date end_date);
    List<Appointment> findAllByEndTimeLessThanEqualAndTookPlace(Date end_date, boolean took_place);
    Integer countAllByStartTimeBetweenAndDoctorOrPatient(Date startDate, Date endDate, Doctor doctor, Patient patient);
    Appointment saveAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    void deleteAppointments();
}
