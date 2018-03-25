package com.healthcare.main.boundry.mapper;

import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;

public class ObjectMapper {

    public static void map2DoctorDb(Doctor doctorDb, Doctor doctorRequest)
    {
        doctorDb.setFirstName(doctorRequest.getFirstName());
        doctorDb.setLastName(doctorRequest.getLastName());
        doctorDb.setSpecialization(doctorRequest.getSpecialization());
    }

    public static void map2PatientDb(Patient patientDb, Patient patientRequest)
    {
        patientDb.setFirstName(patientRequest.getFirstName());
        patientDb.setLastName(patientRequest.getLastName());
        patientDb.setPhoneNumber(patientRequest.getPhoneNumber());
    }
}
