package com.healthcare.main.entity.repository;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDoctorAndPatient(Doctor doctor, Patient patient);
    List<Appointment> findAllByPatient(Patient patient);
    List<Appointment> findAllByDoctor(Doctor doctor);

    @Query("SELECT count(id) FROM Appointment WHERE start_time BETWEEN :fromdate AND :todate AND (doctor_id = :doctor_id OR patient_id=:patient_id)")
    Integer countAllBetweenStartTimeAndEndTimeAndDoctorOrPatient(
            @Param("fromdate") @Temporal(TemporalType.DATE) Date startDay,
            @Param("todate") @Temporal(TemporalType.DATE) Date endDay,
            @Param("doctor_id") Long doctor_id,
            @Param("patient_id") Long patient_id
    );

}
