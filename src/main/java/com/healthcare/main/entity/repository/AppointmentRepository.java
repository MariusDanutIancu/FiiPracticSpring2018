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
    List<Appointment> findAllByDoctorAndEndTimeGreaterThan(Doctor doctor, Date end_date);
    List<Appointment> findAllByEndTimeGreaterThan(Date end_date);
    Integer countAllByStartTimeBetweenAndDoctorOrPatient(Date start_date, Date end_date, Doctor doctor, Patient patient);

//    version 0.2 using data spring jpa - problem repetition because logical operations - TODO: find a better way without using custom query
    Integer countAllByStartTimeBetweenAndDoctorOrStartTimeBetweenAndPatient(Date start_date1, Date end_date1, Doctor doctor, Date start_date2, Date end_date2, Patient patient);

//    version 0.1 using custom query
//    @Query("SELECT count(id) FROM Appointment WHERE start_time BETWEEN :fromdate AND :todate AND (doctor_id = :doctor_id OR patient_id=:patient_id)")
//    Integer countAllByStartTimeBetweenAndDoctorOrPatient(
//            @Param("fromdate") @Temporal(TemporalType.DATE) Date start_date,
//            @Param("todate") @Temporal(TemporalType.DATE) Date end_date,
//            @Param("doctor_id") Long doctor_id,
//            @Param("patient_id") Long patient_id
//    );

    //
}
