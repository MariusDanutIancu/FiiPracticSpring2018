package com.healthcare.main.service;

import com.healthcare.main.model.Doctor;

import java.util.List;

public interface DoctorService
{
    Doctor getDoctor(Long id);
    List<Doctor> getAllDoctors();

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);
    List<Doctor> updateDoctors(List<Doctor> doctorList);

    Boolean deleteDoctor(Long id);
    Boolean deleteAllDoctors();
}
