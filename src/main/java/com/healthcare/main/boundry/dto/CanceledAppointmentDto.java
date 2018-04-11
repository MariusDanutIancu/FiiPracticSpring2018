package com.healthcare.main.boundry.dto;


public class CanceledAppointmentDto
{
    private Long canceled_appointment_id;
    private Long doctor_id;
    private Long patient_id;
    private Boolean cancel;

    public Long getCanceled_appointment_id() {
        return canceled_appointment_id;
    }

    public void setCanceled_appointment_id(Long canceled_appointment_id) {
        this.canceled_appointment_id = canceled_appointment_id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
