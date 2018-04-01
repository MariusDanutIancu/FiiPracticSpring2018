package com.healthcare.main.boundry.mapper;

import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Email;
import com.healthcare.main.entity.model.Patient;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

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

    public static void map2AppointmentDb(Appointment appointmentDB, Appointment appointmentRequest)
    {
        appointmentDB.setDescription(appointmentRequest.getDescription());
        appointmentDB.setDoctor(appointmentRequest.getDoctor());
        appointmentDB.setPatient(appointmentRequest.getPatient());
    }
}
