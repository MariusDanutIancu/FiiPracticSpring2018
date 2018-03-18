package com.healthcare.main.service;

import com.healthcare.main.model.Doctor;

import java.util.List;

public interface DoctorService
{
    Doctor getDoctor(Long id);
    Doctor getAllDoctors();

    Doctor saveDoctor(Doctor doctor);

    Doctor updateDoctor(Long id);
    Doctor updateDoctors(List<Doctor> doctorList);

    Doctor deleteDoctor(Long id);
    Doctor deleteAllDoctors();
}
