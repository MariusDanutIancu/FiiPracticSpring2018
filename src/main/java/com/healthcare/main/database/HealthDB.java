package com.healthcare.main.database;

import com.healthcare.main.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HealthDB
{
    private Map<Long, Doctor> doctors;

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
}
