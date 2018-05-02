package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.DoctorDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.DoctorMapper;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.properties.CustomProperties;
import com.healthcare.main.common.EmailCommon;
import com.sun.jndi.toolkit.url.Uri;
import io.swagger.annotations.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value="/api/0.1/doctors")
public class DoctorController
{
    private DoctorService doctorService;
    private EmailService emailService;
    private MessageSource messageSource;
    private CustomProperties customProps;

    //templates used in error messages
    private static final String DOCTOR_NOT_FOUND_TEMPLATE = "Doctor with id=%s was not found.";

    @Autowired
    public DoctorController(DoctorService doctorService, EmailService emailService, MessageSource messageSource,
                            CustomProperties customProps) {
        this.doctorService = doctorService;
        this.emailService = emailService;
        this.messageSource = messageSource;
        this.customProps = customProps;
    }

    /**
     * Doctor get request using doctor unique id.
     *
     * @param id doctor unique id
     * @return requested doctor
     * @throws NotFoundException no doctor with requested id found in database
     */
    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("id") Long id) throws NotFoundException
    {
        Doctor doctorEntity = doctorService.getDoctor(id);
        if(doctorEntity == null)
        {
            throw new NotFoundException(String.format(DOCTOR_NOT_FOUND_TEMPLATE, id));
        }
        return ResponseEntity.ok().body(DoctorMapper.MAPPER.toDoctorDto(doctorEntity));
    }

    /**
     * Doctor get request
     *
     * @return all doctors that are in the database
     * @throws NotFoundException no doctors found in the database
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<DoctorDto> getDoctors() throws NotFoundException
    {
        List<Doctor> doctorListEntity = doctorService.getAllDoctors();
        if(doctorListEntity.isEmpty())
        {
            throw new NotFoundException("There are no doctors in the database.");
        }
        return DoctorMapper.MAPPER.toDoctorsDto(doctorListEntity);
    }

    /**
     * Saves a doctor in the database
     *
     * @param doctorDto doctor data that needs to be saved
     * @return the saved doctor
     */
    @PostMapping
    public ResponseEntity<DoctorDto> postDoctor(@RequestBody DoctorDto doctorDto, UriComponentsBuilder ucBuilder)
    {
        Doctor doctor = DoctorMapper.MAPPER.toDoctor(doctorDto);
        doctor = doctorService.saveDoctor(doctor);

        String message = String.format(messageSource.getMessage("account.created.doctor",
                null, Locale.getDefault()), customProps.getDoctorssurl()) + doctor.getId();;

        emailService.sendEmailHttp(emailService.getEmail(doctor, "Account created",
                String.format(message, doctor.getId())));

        HttpHeaders headers = new HttpHeaders();
        URI uri = ucBuilder.path("/api/0.1/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        headers.setLocation(uri);

        return ResponseEntity.created(uri).headers(headers).body(DoctorMapper.MAPPER.toDoctorDto(doctor));
    }

    /**
     * Saves a list of doctors in the database
     *
     * @param id doctor unique id
     * @param doctor doctor data that needs to be saved
     * @return null
     * @throws MethodNotAllowedException this method is not allowed
     */
    @PostMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Doctor saveDoctor_not_allowed(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    /**
     * Updates a doctor in the database
     *
     * @param id doctor unique id
     * @param doctorDto doctor data that needs to be saved
     * @return updated doctor
     * @throws BadRequestException the ids from url and request body does not match
     * @throws NotFoundException doctor not found
     */
    @PutMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public DoctorDto updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorDto doctorDto) throws BadRequestException, NotFoundException
    {
        if(!id.equals(doctorDto.getDoctor_id())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        Doctor doctorEntity = doctorService.getDoctor(id);
        if(doctorEntity == null){
            throw new NotFoundException(String.format(DOCTOR_NOT_FOUND_TEMPLATE, id));
        }

        DoctorMapper.MAPPER.toDoctor(doctorDto, doctorEntity);
        return DoctorMapper.MAPPER.toDoctorDto( doctorService.updateDoctor(doctorEntity));
    }

    /**
     * Delete a doctor
     *
     * @param id doctor unique id
     * @throws NotFoundException the requested doctor was not found
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) throws NotFoundException
    {
        Doctor doctorDb  = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format(DOCTOR_NOT_FOUND_TEMPLATE, id));
        }
        doctorService.deleteDoctor(doctorDb);
    }

    /**
     * Delete all doctors
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllDoctors()
    {
        doctorService.deleteAllDoctors();
    }
}