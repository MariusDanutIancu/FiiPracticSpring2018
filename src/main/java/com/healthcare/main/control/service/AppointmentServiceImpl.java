package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.entity.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{


    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentRepository.findAllByPatient(patient);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentRepository.findAllByDoctor(doctor);
    }

    @Override
    public List<Appointment> getAppointmentsDoctorAndPatient(Doctor doctor, Patient patient) {
        return appointmentRepository.findAllByDoctorAndPatient(doctor, patient);
    }

    @Override
    public List<Appointment> findAllByDoctorAndEndTimeGreaterThan(Doctor doctor, Date end_date) {
        return appointmentRepository.findAllByDoctorAndEndTimeGreaterThan(doctor, end_date);
    }

    @Override
    public List<Appointment> findAllByEndTimeGreaterThan(Date end_date) {
        return appointmentRepository.findAllByEndTimeGreaterThan(end_date);
    }


    @Override
    public List<Appointment> findAllByTookPlace(boolean took_place) {
        return appointmentRepository.findAllByTookPlace(took_place);
    }

    @Override
    public List<Appointment> findAllByEndTimeLessThanEqual(Date end_date) {
        return appointmentRepository.findAllByEndTimeLessThanEqual(end_date);
    }

    @Override
    public List<Appointment> findAllByEndTimeLessThanEqualAndTookPlace(Date end_date, boolean took_place) {
        return appointmentRepository.findAllByEndTimeLessThanEqualAndTookPlace(end_date, took_place);
    }

    @Override
    public Integer countAllByStartTimeBetweenAndDoctorOrPatient(Date startDate, Date endDate, Doctor doctor, Patient patient) {
        return appointmentRepository.countAllByStartTimeBetweenAndDoctorOrStartTimeBetweenAndPatient(startDate, endDate, doctor, startDate, endDate, patient);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);

    }

    @Override
    public void deleteAppointments() {
        appointmentRepository.deleteAll();
    }

    @Override
    public Page<Appointment> appointmentPageable(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

}
