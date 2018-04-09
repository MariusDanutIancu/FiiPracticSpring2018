package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.DoctorDto;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.DoctorMapper;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.control.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping(value="/api/0.1/doctors")
public class DoctorController
{
    private DoctorService doctorService;
    private JavaMailSender javaMailSender;

    @Autowired
    public DoctorController(DoctorService doctorService, JavaMailSender javaMailSender) {
        this.doctorService = doctorService;
        this.javaMailSender = javaMailSender;
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
//
//    /**
//     *
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping
//    public List<Doctor> getDoctors() throws NotFoundException
//    {
//        List<Doctor> doctorListDb = doctorService.getAllDoctors();
//        if(doctorListDb.size() == 0)
//        {
//            throw new NotFoundException("There are no doctors in the database.");
//        }
//        return doctorListDb;
//    }
//
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
        //this.sendEmail(doctor);
        return DoctorMapper.MAPPER.fromDoctor(doctor);
    }
//
//    /**
//     *
//     * @param id
//     * @param doctor
//     * @return
//     * @throws MethodNotAllowedException
//     */
//    @PostMapping(value="/{id}")
//    public Doctor saveDoctor_not_allowed(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws MethodNotAllowedException
//    {
//        throw new MethodNotAllowedException("Method is not allowed.");
//    }
//
//    /**
//     *
//     * @param id
//     * @param doctor
//     * @return
//     * @throws BadRequestException
//     * @throws NotFoundException
//     */
//    @PutMapping(value="/{id}")
//    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws BadRequestException, NotFoundException
//    {
//        if(!id.equals(doctor.getDoctorID())){
//            throw new BadRequestException("The id is not the same with id from object");
//        }
//
//        Doctor doctorDb = doctorService.getDoctor(id);
//        if(doctorDb == null){
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
//        }
//
//        ObjectMapper.map2DoctorDb(doctorDb, doctor);
//        return  doctorService.updateDoctor(doctorDb);
//    }
//
//    /**
//     *
//     * @param id
//     * @throws NotFoundException
//     */
//    @DeleteMapping(value="/{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteDoctor(@PathVariable Long id) throws NotFoundException
//    {
//        Doctor doctorDb  = doctorService.getDoctor(id);
//        if(doctorDb == null){
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
//        }
//        doctorService.deleteDoctor(doctorDb);
//    }
//
//    /**
//     *
//     */
//    @DeleteMapping
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteAllDoctors()
//    {
//        doctorService.deleteAllDoctors();
//    }
//
    /**
     *
     * @param doctor
     */
    private void sendEmail(Doctor doctor)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(doctor.getEmail().getEmail());
            mimeMessageHelper.setSubject("Account created");
            mimeMessageHelper.setText(String.format("You can see your data at %s", "http://localhost:8080/api/0.1/doctors/" + doctor.getId()));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}