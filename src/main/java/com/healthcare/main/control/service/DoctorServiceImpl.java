package com.healthcare.main.control.service;


import com.healthcare.main.entity.repository.DoctorRepository;
import com.healthcare.main.entity.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService
{
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor getDoctor(Long id)
    {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctors()
    {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor)
    {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor)
    {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> updateDoctors(List<Doctor> doctorList)
    {
        for(Doctor doctor:doctorList)
        {
            doctorRepository.save(doctor);
        }
        return doctorList;
    }

    @Override
    public void deleteDoctor(Doctor doctor)
    {
        doctorRepository.delete(doctor);
    }

    @Override
    public void deleteAllDoctors()
    {
        doctorRepository.deleteAll();
    }
}
