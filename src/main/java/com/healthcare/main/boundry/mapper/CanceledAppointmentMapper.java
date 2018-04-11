package com.healthcare.main.boundry.mapper;

import com.healthcare.main.boundry.dto.CanceledAppointmentDto;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.CanceledAppointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CanceledAppointmentMapper {

    CanceledAppointmentMapper MAPPER = Mappers.getMapper( CanceledAppointmentMapper.class);

    @Mappings({
            @Mapping(source = "canceledAppointment.id", target = "canceled_appointment_id"),
            @Mapping(source = "canceledAppointment.canceled", target = "cancel"),
            @Mapping(source = "appointment.doctor.id", target = "doctor_id"),
            @Mapping(source = "appointment.patient.id", target = "patient_id"),
    })
    CanceledAppointmentDto fromCanceledAppointmentAndAppointment(CanceledAppointment canceledAppointment, Appointment appointment);

    @Mappings({
            @Mapping(source = "canceled_appointment_id", target = "id"),
            @Mapping(source = "cancel", target = "canceled"),
    })
    CanceledAppointment toCanceledAppointment(CanceledAppointmentDto canceledAppointmentDto);
}
