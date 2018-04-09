package com.healthcare.main.boundry.mapper;

import com.healthcare.main.boundry.dto.AppointmentDto;
import com.healthcare.main.entity.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper MAPPER = Mappers.getMapper( AppointmentMapper.class );

    @Mappings({
            @Mapping(source = "id", target = "appointment_id"),
            @Mapping(source = "doctor_id", target = "doctor_id"),
            @Mapping(source = "patient_id", target = "patient_id"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "endTime", target = "endTime"),
            @Mapping(source = "cause", target = "cause"),
    })
    AppointmentDto fromAppoinment(Appointment appointment);

    @Mappings({
            @Mapping(source = "appointment_id", target = "id"),
            @Mapping(source = "doctor_id", target = "doctor_id"),
            @Mapping(source = "patient_id", target = "patient_id"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "endTime", target = "endTime"),
            @Mapping(source = "cause", target = "cause"),
    })
    Appointment toAppointment(AppointmentDto AppointmentDto);
}
