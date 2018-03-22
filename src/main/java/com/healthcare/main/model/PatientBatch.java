package com.healthcare.main.model;

import java.util.ArrayList;
import java.util.List;

public class PatientBatch
{
    private List<Patient> patientList;

    public PatientBatch()
    {
        this.patientList = new ArrayList<>();
    }

    public PatientBatch(List<Patient> patientList)
    {
        this.patientList = patientList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}
