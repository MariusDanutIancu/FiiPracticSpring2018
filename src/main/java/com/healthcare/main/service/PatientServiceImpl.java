package com.healthcare.main.service;

import com.healthcare.main.database.HealthDB;
import com.healthcare.main.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private HealthDB healthDB;

    @Autowired
    public PatientServiceImpl(HealthDB healthDB) {
        this.healthDB = healthDB;
    }

    @Override
    public Patient getPatient(Long id) {
        return healthDB.getPatient(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return healthDB.getAllPatients();
    }

    @Override
    public Patient savePatient(Patient patient) {
        return healthDB.savePatient(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return healthDB.updatePatient(patient);
    }

    @Override
    public List<Patient> updatePatients(List<Patient> patientList) {
        return healthDB.updatePatients(patientList);
    }

    @Override
    public Boolean deletePatient(Long id) {
        return healthDB.deletePatient(id);
    }

    @Override
    public Boolean deleteAllPatients() {
        return healthDB.deleteAllPatients();
    }
}
