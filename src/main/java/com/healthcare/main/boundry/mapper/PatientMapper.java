package com.healthcare.main.boundry.mapper;

import com.healthcare.main.boundry.dto.PatientDto;
import com.healthcare.main.entity.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper MAPPER = Mappers.getMapper( PatientMapper.class );

    @Mappings({
            @Mapping(source = "id", target = "patient_id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    PatientDto fromPatient(Patient patient);

    @Mappings({
            @Mapping(source = "patient_id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    Patient toPatient(PatientDto patientDto);

    @Mappings({
            @Mapping(source = "patient_id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "phoneNumber.phoneNumber", target = "phoneNumber.phoneNumber"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "email.email", target = "email.email"),
    })
    void toPatient(PatientDto patientDto, @MappingTarget Patient patientEntity);
}
