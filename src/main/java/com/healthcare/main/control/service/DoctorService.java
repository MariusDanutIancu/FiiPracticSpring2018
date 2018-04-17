package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Doctor;

import java.util.List;

public interface DoctorService
{
    /**
     *
     * @param id
     * @return
     */
    Doctor getDoctor(Long id);

    /**
     *
     * @return
     */
    List<Doctor> getAllDoctors();

    /**
     *
     * @param doctor
     * @return
     */
    Doctor saveDoctor(Doctor doctor);

    /**
     *
     * @param doctor
     * @return
     */
    Doctor updateDoctor(Doctor doctor);

    /**
     *
     * @param doctorList
     * @return
     */
    List<Doctor> updateDoctors(List<Doctor> doctorList);

    /**
     *
     * @param doctor
     */
    void deleteDoctor(Doctor doctor);

    /**
     *
     */
    void deleteAllDoctors();
}
