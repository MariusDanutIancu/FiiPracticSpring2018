package com.healthcare.main.entity.repository;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //List<Appointment> findAllByDoctorAndPatient(Doctor doctor, Patient patient);
    //List<Appointment> findAllByPatient(Patient patient);
    //List<Appointment> findAllByDoctor(Doctor doctor);
}
