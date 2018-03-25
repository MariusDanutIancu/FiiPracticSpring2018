package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.entity.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService
{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient getPatient(Long id)
    {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> getAllPatients()
    {
        return patientRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient)
    {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient)
    {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> updatePatients(List<Patient> patientList)
    {
        for(Patient doctor:patientList)
        {
            patientRepository.save(doctor);
        }
        return patientList;
    }

    @Override
    public void deletePatient(Patient patient)
    {
        patientRepository.delete(patient);
    }

    @Override
    public void deleteAllPatients()
    {
        patientRepository.deleteAll();
    }
}
