package com.healthcare.main.entity.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorBatch
{
    private List<Doctor> doctorList;

    public DoctorBatch()
    {
        this.doctorList = new ArrayList<>();
    }

    public DoctorBatch(List<Doctor> doctorList)
    {
        this.doctorList = doctorList;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
