package com.healthcare.main.boundry.mapper;

import com.healthcare.main.boundry.dto.DoctorDto;
import com.healthcare.main.entity.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
//@Mapper(componentModel="spring")
public interface DoctorMapper {

    DoctorMapper MAPPER = Mappers.getMapper( DoctorMapper.class );

    @Mappings({
            @Mapping(source = "id", target = "doctor_id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "function", target = "function"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    DoctorDto toDoctorDto(Doctor doctor);
    List<DoctorDto> toDoctorsDto(List<Doctor> doctors);

    @Mappings({
            @Mapping(source = "doctor_id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "function", target = "function"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    Doctor toDoctor(DoctorDto doctorDto);

    @Mappings({
            @Mapping(source = "doctor_id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "function", target = "function"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    void toDoctor(DoctorDto doctorDto, @MappingTarget Doctor doctorEntity);
}
