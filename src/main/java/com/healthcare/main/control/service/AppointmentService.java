package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface AppointmentService {

    /**
     *
     * @param id
     * @return
     */
    Appointment getAppointment(Long id);

    /**
     *
     * @return
     */
    List<Appointment> getAppointments();

    /**
     *
     * @param patient
     * @return
     */
    List<Appointment> getAppointmentsByPatient(Patient patient);

    /**
     *
     * @param doctor
     * @return
     */
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);

    /**
     *
     * @param doctor
     * @param patient
     * @return
     */
    List<Appointment> getAppointmentsDoctorAndPatient(Doctor doctor, Patient patient);

    /**
     *
     * @param doctor
     * @param end_date
     * @return
     */
    List<Appointment> findAllByDoctorAndEndTimeGreaterThan(Doctor doctor, Date end_date);

    /**
     *
     * @param end_date
     * @return
     */
    List<Appointment> findAllByEndTimeGreaterThan(Date end_date);

    /**
     *
     * @param took_place
     * @return
     */
    List<Appointment> findAllByTookPlace(boolean took_place);

    /**
     *
     * @param end_date
     * @return
     */
    List<Appointment> findAllByEndTimeLessThanEqual(Date end_date);

    /**
     *
     * @param end_date
     * @param took_place
     * @return
     */
    List<Appointment> findAllByEndTimeLessThanEqualAndTookPlace(Date end_date, boolean took_place);

    /**
     *
     * @param startDate
     * @param endDate
     * @param doctor
     * @param patient
     * @return
     */
    Integer countAllByStartTimeBetweenAndDoctorOrPatient(Date startDate, Date endDate, Doctor doctor, Patient patient);

    /**
     *
     * @param appointment
     * @return
     */
    Appointment saveAppointment(Appointment appointment);

    /**
     *
     * @param appointment
     */
    void deleteAppointment(Appointment appointment);

    /**
     *
     */
    void deleteAppointments();

    /**
     *
     * @param pageable
     * @return
     */
    Page<Appointment> appointmentPageable(Pageable pageable);
}
