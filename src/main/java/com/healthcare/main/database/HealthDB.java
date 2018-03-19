package com.healthcare.main.database;

import com.healthcare.main.model.Doctor;
import com.healthcare.main.model.Patient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HealthDB
{
    private Map<Long, Doctor> doctors;
    private Map<Long, Patient> patients;

    /**
     * Class default constructor
     */
    public HealthDB(){
        this.doctors = new HashMap<>();

        Doctor doctor_0 = new Doctor();
        doctor_0.setDoctorID(1L);
        doctor_0.setFirstName("name");
        doctor_0.setSpecialization("function");

        Doctor doctor_1 = new Doctor();
        doctor_1.setDoctorID(2L);
        doctor_1.setFirstName("name");
        doctor_1.setSpecialization("function");

        this.doctors.put(doctor_0.getDoctorID(), doctor_0);
        this.doctors.put(doctor_1.getDoctorID(), doctor_1);

        this.patients = new HashMap<>();

        Patient patient_0 = new Patient();
        patient_0.setPatientID(1L);
        patient_0.setFirstName("name");
        patient_0.setLastName("name");

        Patient patient_1 = new Patient();
        patient_1.setPatientID(2L);
        patient_1.setFirstName("name");
        patient_1.setLastName("name");

        this.patients.put(patient_0.getPatientID(), patient_0);
        this.patients.put(patient_1.getPatientID(), patient_1);
    }

    /**
     *
     * @param id doctor unique id
     * @return requested doctor object based on the unique id
     */
    public Doctor getDoctor(Long id) {
        return this.doctors.get(id);
    }

    /**
     *
     * @return an array list with all the doctors in the database
     */
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }

    /**
     *
     * @param doctor the doctor that needs to be saved in the database
     * @return the doctor that has been saved
     */
    public Doctor saveDoctor(Doctor doctor) {
        doctor.setDoctorID(ThreadLocalRandom.current().nextLong(0, 10000));
        this.doctors.put(doctor.getDoctorID(), doctor);
        return doctor;
    }

    /**
     *
     * @param doctor the doctor that to be updated in the database
     * @return the doctor that has been updated
     */
    public Doctor updateDoctor(Doctor doctor) {
        this.doctors.put(doctor.getDoctorID(), doctor);
        return doctor;
    }

    /**
     *
     * @param doctorList a list of doctors that needs to be updated
     * @return the list of doctors that has been updated
     */
    public List<Doctor> updateDoctors(List<Doctor> doctorList) {

        for (Doctor doctor:doctorList) {
            this.doctors.put(doctor.getDoctorID(), doctor);
        }
        return doctorList;
    }

    /**
     *
     * @param doctor the doctor that will be deleted
     * @return true if the doctor has been deleted, false otherwise
     */
    public Boolean deleteDoctor(Doctor doctor) {
        this.doctors.remove(doctor.getDoctorID());
        return true;
    }

    /**
     * Deletes all doctors in the database
     * @return True if the process has been completed, false otherwise
     */
    public Boolean deleteAllDoctors() {
        this.doctors.clear();
        return true;
    }

    /**
     *
     * @param id patient unique id
     * @return requested patient object based on the unique id
     */
    public Patient getPatient(Long id) {
        return this.patients.get(id);
    }

    /**
     *
     * @return an array list with all the patients in the database
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    /**
     *
     * @param patient the patient that needs to be saved in the database
     * @return the patient that has been saved
     */
    public Patient savePatient(Patient patient) {
        patient.setPatientID(ThreadLocalRandom.current().nextLong(0, 10000));
        this.patients.put(patient.getPatientID(), patient);
        return patient;
    }

    /**
     *
     * @param patient the patient that to be updated in the database
     * @return the patient that has been updated
     */
    public Patient updatePatient(Patient patient) {
        this.patients.put(patient.getPatientID(), patient);
        return patient;
    }

    /**
     *
     * @param patientList a list of patients that needs to be updated
     * @return the list of patients that has been updated
     */
    public List<Patient> updatePatients(List<Patient> patientList) {

        for (Patient patient:patientList) {
           this.patients.put(patient.getPatientID(), patient);
        }
        return patientList;
    }

    /**
     *
     * @param patient the patient that will be deleted
     * @return true if the patient has been deleted, false otherwise
     */
    public Boolean deletePatient(Patient patient) {
        this.patients.remove(patient.getPatientID());
        return true;
    }

    /**
     * Deletes all patients in the database
     * @return True if the process has been completed, false otherwise
     */
    public Boolean deleteAllPatients() {
        this.patients.clear();
        return true;
    }
}
