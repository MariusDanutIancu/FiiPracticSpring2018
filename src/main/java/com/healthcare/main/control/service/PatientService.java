package com.healthcare.main.control.service;

import com.healthcare.main.entity.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     *
     * @param id
     * @return
     */
    Patient getPatient(Long id);

    /**
     *
     * @return
     */
    List<Patient> getAllPatients();

    /**
     *
     * @param patient
     * @return
     */
    Patient savePatient(Patient patient);

    /**
     *
     * @param patient
     * @return
     */
    Patient updatePatient(Patient patient);

    /**
     *
     * @param patientList
     * @return
     */
    List<Patient> updatePatients(List<Patient> patientList);

    /**
     *
     * @param patient
     */
    void deletePatient(Patient patient);

    /**
     *
     */
    void deleteAllPatients();
}
