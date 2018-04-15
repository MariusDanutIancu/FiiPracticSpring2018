package com.healthcare.main.boundry.mapper;

import com.healthcare.main.boundry.dto.AppointmentDto;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface AppointmentMapper {

    AppointmentMapper MAPPER = Mappers.getMapper( AppointmentMapper.class );

    @Mappings({
            @Mapping(source = "appointment.id", target = "appointment_id"),
            @Mapping(source = "appointment.doctor.id", target = "doctor_id"),
            @Mapping(source = "appointment.patient.id", target = "patient_id"),
            @Mapping(source = "appointment.startTime", target = "startTime"),
            @Mapping(source = "appointment.endTime", target = "endTime"),
            @Mapping(source = "appointment.cause", target = "cause"),
            @Mapping(source = "appointment.canceledAppointment.canceled", target = "cancel")
    })
    AppointmentDto toAppointmentDto(Appointment appointment);
    List<AppointmentDto> toAppointmentsDto(List<Appointment> appointments);

    @Mappings({
            @Mapping(source = "appointmentDto.appointment_id", target = "id"),
            @Mapping(source = "doctor", target = "doctor"),
            @Mapping(source = "patient", target = "patient"),
            @Mapping(source = "appointmentDto.startTime", target = "startTime"),
            @Mapping(source = "appointmentDto.endTime", target = "endTime"),
            @Mapping(source = "appointmentDto.cause", target = "cause"),
            @Mapping(source = "appointmentDto.cancel", target = "canceledAppointment.canceled")
    })
    Appointment toAppointment(Doctor doctor, Patient patient, AppointmentDto appointmentDto);

    @Mappings({
            @Mapping(source = "appointmentDto.appointment_id", target = "id"),
            @Mapping(source = "doctor", target = "doctor"),
            @Mapping(source = "patient", target = "patient"),
            @Mapping(source = "appointmentDto.startTime", target = "startTime"),
            @Mapping(source = "appointmentDto.endTime", target = "endTime"),
            @Mapping(source = "appointmentDto.cause", target = "cause"),
            @Mapping(source = "appointmentDto.cancel", target = "canceledAppointment.canceled")
    })
    void toAppointment(Doctor doctor, Patient patient, AppointmentDto appointmentDto, @MappingTarget Appointment appointment);
}
