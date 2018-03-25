package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatient(Long id);
    List<Patient> getAllPatients();

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);
    List<Patient> updatePatients(List<Patient> patientList);

    void deletePatient(Patient patient);
    void deleteAllPatients();
}
