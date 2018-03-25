package com.healthcare.main.control.service;


import com.healthcare.main.entity.repository.HealthDB;
import com.healthcare.main.entity.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService
{
    private HealthDB healthDB;

    @Autowired
    public DoctorServiceImpl(HealthDB healthDB) {
        this.healthDB = healthDB;
    }

    @Override
    public Doctor getDoctor(Long id) {
        return healthDB.getDoctor(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return healthDB.getAllDoctors();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return healthDB.saveDoctor(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return healthDB.updateDoctor(doctor);
    }

    @Override
    public List<Doctor> updateDoctors(List<Doctor> doctorList) {
        return healthDB.updateDoctors(doctorList);
    }

    @Override
    public Boolean deleteDoctor(Doctor doctor) {
        return healthDB.deleteDoctor(doctor);
    }

    @Override
    public Boolean deleteAllDoctors() {
        return healthDB.deleteAllDoctors();
    }
}
