package com.healthcare.main.database;

import com.healthcare.main.model.Doctor;
import com.healthcare.main.model.Patient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HealthDB
{
    private Map<Long, Doctor> doctors;
    private Map<Long, Patient> patients;

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

    public Doctor getDoctor(Long id) {
        return doctors.get(id);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }

    public Doctor saveDoctor(Doctor doctor) {
        this.doctors.put(doctor.getDoctorID(), doctor);
        return doctor;
    }

    public Doctor updateDoctor(Doctor doctor) {
        doctors.put(doctor.getDoctorID(), doctor);
        return doctor;
    }

    public List<Doctor> updateDoctors(List<Doctor> doctorList) {

        for (Doctor doctor:doctorList) {
            doctors.put(doctor.getDoctorID(), doctor);
        }
        return doctorList;
    }

    public Boolean deleteDoctor(Long id) {
        doctors.remove(id);
        return true;
    }

    public Boolean deleteAllDoctors() {
        doctors.clear();
        return true;
    }

    public Patient getPatient(Long id) {
        return patients.get(id);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    public Patient savePatient(Patient patient) {
        this.patients.put(patient.getPatientID(), patient);
        return patient;
    }

    public Patient updatePatient(Patient patient) {
        patients.put(patient.getPatientID(), patient);
        return patient;
    }

    public List<Patient> updatePatients(List<Patient> patientList) {

        for (Patient patient:patientList) {
            patients.put(patient.getPatientID(), patient);
        }
        return patientList;
    }

    public Boolean deletePatient(Long id) {
        patients.remove(id);
        return true;
    }

    public Boolean deleteAllPatients() {
        patients.clear();
        return true;
    }
}
