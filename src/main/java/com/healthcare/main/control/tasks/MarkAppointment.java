package com.healthcare.main.control.tasks;

import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.entity.model.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MarkAppointment {

    private AppointmentService appointmentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MarkAppointment.class);

    @Autowired
    public MarkAppointment(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Scheduled(fixedDelay = 300000)
    public void markAppointments() {

        LOGGER.info("Marking appointments started");

        Date current_date = new Date();
        Long counter = 0L;
        List<Appointment> appointmentList = appointmentService.findAllByEndTimeLessThanEqualAndTookPlace(current_date, false);

        for(Appointment appointment:appointmentList)
        {
            appointment.setTookPlace(true);
            appointmentService.saveAppointment(appointment);
            counter++;
            LOGGER.info("Marked appointment with id {}", appointment.getId());
        }

        LOGGER.info("Marked {} appointments", counter);
    }
}
