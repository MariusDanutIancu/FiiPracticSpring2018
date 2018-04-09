package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.control.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

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

//    /**
//     *
//     * @param id
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping(value="/{id}")
//    public Doctor getDoctor(@PathVariable("id") Long id) throws NotFoundException
//    {
//        Doctor doctor = doctorService.getDoctor(id);
//        if(doctor == null)
//        {
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
//        }
//        return doctor;
//    }
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
//    /**
//     *
//     * @param doctor
//     * @return
//     */
//    @PostMapping
//    @ResponseStatus(value = HttpStatus.CREATED)
//    public Doctor saveDoctor(@RequestBody Doctor doctor)
//    {
//        Doctor doctorDb = doctorService.saveDoctor(doctor);
//        sendEmail(doctorDb);
//        return doctorDb;
//    }
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
//    /**
//     *
//     * @param doctor
//     */
//    private void sendEmail(Doctor doctor)
//    {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//
//        try {
//            mimeMessageHelper.setTo(doctor.getEmail().getEmail());
//            mimeMessageHelper.setSubject("Account created");
//            mimeMessageHelper.setText(String.format("Doctor name: %s", doctor.getFirstName() + doctor.getLastName()));
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}