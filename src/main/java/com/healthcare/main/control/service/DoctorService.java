package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Doctor;

import java.util.List;

public interface DoctorService
{
    Doctor getDoctor(Long id);
    List<Doctor> getAllDoctors();

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);
    List<Doctor> updateDoctors(List<Doctor> doctorList);

    void deleteDoctor(Doctor doctor);
    void deleteAllDoctors();
}
