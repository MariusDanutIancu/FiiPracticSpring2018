package com.healthcare.main.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.custom.props")
public class CustomProperties
{
    private String url = "http://localhost:8080/";
    private String appointmentsurl = "http://localhost:8080/appointments/";
    private String doctorssurl = "http://localhost:8080/doctors/";
    private String patientsurl = "http://localhost:8080/patients/";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppointmentsurl() {
        return appointmentsurl;
    }

    public void setAppointmentsurl(String appointmentsurl) {
        this.appointmentsurl = appointmentsurl;
    }

    public String getDoctorssurl() {
        return doctorssurl;
    }

    public void setDoctorssurl(String doctorssurl) {
        this.doctorssurl = doctorssurl;
    }

    public String getPatientsurl() {
        return patientsurl;
    }

    public void setPatientsurl(String patientsurl) {
        this.patientsurl = patientsurl;
    }
}

