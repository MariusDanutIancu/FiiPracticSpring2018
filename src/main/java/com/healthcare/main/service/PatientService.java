package com.healthcare.main.service;

import com.healthcare.main.model.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatient(Long id);
    List<Patient> getAllPatients();

    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);
    List<Patient> updatePatients(List<Patient> patientList);

    Boolean deletePatient(Long id);
    Boolean deleteAllPatients();
}
