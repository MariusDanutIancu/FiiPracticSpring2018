package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.entity.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Integer countAllBetweenStartTimeAndEndTimeAndDoctorOrPatient(Date startDate, Date endDate, Long doctorId, Long patientId) {
        return appointmentRepository.countAllBetweenStartTimeAndEndTimeAndDoctorOrPatient(startDate, endDate, doctorId, patientId);
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
}
