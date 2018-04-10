package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.DoctorDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.DoctorMapper;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.util.email.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/0.1/doctors")
public class DoctorController
{
    private DoctorService doctorService;
    private EmailService emailService;

    @Autowired
    public DoctorController(DoctorService doctorService, EmailService emailService) {
        this.doctorService = doctorService;
        this.emailService = emailService;
    }

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping(value="/{id}")
    public DoctorDto getDoctor(@PathVariable("id") Long id) throws NotFoundException
    {
        Doctor doctor = doctorService.getDoctor(id);
        if(doctor == null)
        {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return DoctorMapper.MAPPER.fromDoctor(doctor);
    }

    /**
     *
     * @return
     * @throws NotFoundException
     */
    @GetMapping
    public List<DoctorDto> getDoctors() throws NotFoundException
    {
        List<Doctor> doctorListDb = doctorService.getAllDoctors();
        if(doctorListDb.size() == 0)
        {
            throw new NotFoundException("There are no doctors in the database.");
        }

        List<DoctorDto> doctorDtos = new ArrayList<>();
        for(Doctor doctor:doctorListDb)
        {
            doctorDtos.add(DoctorMapper.MAPPER.fromDoctor(doctor));
        }
        return doctorDtos;
    }

    /**
     *
     * @param doctorDto
     * @return
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public DoctorDto saveDoctor(@RequestBody DoctorDto doctorDto)
    {
        Doctor doctor = DoctorMapper.MAPPER.toDoctor(doctorDto);
        doctor = doctorService.saveDoctor(doctor);
        this.sendEmail(doctor, "Account created", String.format("You can see your data at %s",
                "http://localhost:8080/api/0.1/doctors/" + doctor.getId()));
        return DoctorMapper.MAPPER.fromDoctor(doctor);
    }

    /**
     *
     * @param id
     * @param doctor
     * @return
     * @throws MethodNotAllowedException
     */
    @PostMapping(value="/{id}")
    public Doctor saveDoctor_not_allowed(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    /**
     *
     * @param id
     * @param doctorDto
     * @return
     * @throws BadRequestException
     * @throws NotFoundException
     */
    @PutMapping(value="/{id}")
    public DoctorDto updateDoctor(@PathVariable("id") Long id, @RequestBody DoctorDto doctorDto) throws BadRequestException, NotFoundException
    {
        if(!id.equals(doctorDto.getDoctor_id())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }

        DoctorMapper.MAPPER.toDoctor(doctorDto, doctorDb);
        doctorDb = doctorService.updateDoctor(doctorDb);

        return DoctorMapper.MAPPER.fromDoctor(doctorDb);
    }

    /**
     *
     * @param id
     * @throws NotFoundException
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable Long id) throws NotFoundException
    {
        Doctor doctorDb  = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        doctorService.deleteDoctor(doctorDb);
    }

    /**
     *
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllDoctors()
    {
        doctorService.deleteAllDoctors();
    }

    /**
     *
     * @param doctor
     * @param subject
     * @param message
     */
    @SuppressWarnings("Duplicates")
    private void sendEmail(Doctor doctor, String subject, String message)
    {
        EmailUtil email = new EmailUtil();
        email.setFrom("test.demo.fii.practic.spring.2018@gmail.com");
        email.setTo(doctor.getEmail().getEmail());
        email.setSubject(subject);

        Map<String, String> content = new HashMap<>();
        content.put("name", doctor.getFirstName() + " " + doctor.getLastName());
        content.put("message", message);
        email.setContent(content);

        emailService.sendEmailHttp(email);
    }
}