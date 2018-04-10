package com.healthcare.main.control.tasks;

import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.entity.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MarkAppointment {

    private AppointmentService appointmentService;

    @Autowired
    public MarkAppointment(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Scheduled(fixedDelay = 300000)
    public void reportCurrentTime() {
        Date current_date = new Date();
        List<Appointment> appointmentList = appointmentService.findAllByEndTimeLessThanEqualAndTookPlace(current_date, false);

        for(Appointment appointment:appointmentList)
        {
            appointment.setTookPlace(true);
            appointmentService.saveAppointment(appointment);
        }
    }
}
