package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.AppointmentDto;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.AppointmentMapper;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.entity.model.Appointment;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.Patient;
import com.healthcare.main.entity.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;


@RestController
@RequestMapping(value="/api/0.1/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;
    private JavaMailSender javaMailSender;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService,
                                 PatientService patientService, JavaMailSender javaMailSender) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.javaMailSender = javaMailSender;
    }

//    /**
//     * Appointment get request using unique id.
//     * @param id appointment unique id
//     * @return an appointment
//     * @throws NotFoundException no appointment with requested id found in database
//     */
//    @GetMapping(value="/{id}")
//    public Appointment getAppointment(@PathVariable("id") Long id) throws NotFoundException
//    {
//        Appointment appointmentDb = appointmentService.getAppointment(id);
//        if(appointmentDb == null)
//        {
//            throw new NotFoundException(String.format("appointmentDb with id=%s was not found.", id));
//        }
//        return appointmentDb;
//    }
//
//    /**
//     * Appointment get request
//     * @return all appointments
//     * @throws NotFoundException no appointments found in the database
//     */
//    @GetMapping()
//    public List<Appointment> getAllAppointments() throws NotFoundException
//    {
//        List<Appointment> appointmentListDb = appointmentService.getAppointments();
//        if(appointmentListDb.size() == 0)
//        {
//            throw new NotFoundException("There are no appointments in the database.");
//        }
//        return appointmentListDb;
//    }
//
//    /**
//     *
//     * @param patientid
//     * @param doctorid
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping(value="/filter", params = { "patientid", "doctorid" })
//    public List<Appointment> findAllByDoctorAndPatient(@RequestParam("patientid") Long patientid, @RequestParam("doctorid") Long doctorid) throws NotFoundException
//    {
//        Doctor doctorDb = doctorService.getDoctor(doctorid);
//        if(doctorDb == null){
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
//        }
//
//        Patient patientDB = patientService.getPatient(patientid);
//        if(patientDB == null){
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", patientid));
//        }
//
//        return appointmentService.getAppointmentsDoctorAndPatient(doctorDb, patientDB);
//    }
//
//    /**
//     *
//     * @param doctorid
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping(value="/filter", params = "doctorid" )
//    public List<Appointment> findByDoctor(@RequestParam("doctorid") Long doctorid) throws NotFoundException
//    {
//        Doctor doctorDb = doctorService.getDoctor(doctorid);
//        if(doctorDb == null){
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
//        }
//
//        return appointmentService.getAppointmentsByDoctor(doctorDb);
//    }
//
//    /**
//     *
//     * @param patientid
//     * @return
//     * @throws NotFoundException
//     */
//    @GetMapping(value="/filter", params = "patientid")
//    public List<Appointment> findByPatient(@RequestParam("patientid") Long patientid) throws NotFoundException
//    {
//        Patient patientDB = patientService.getPatient(patientid);
//        if(patientDB == null){
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", patientid));
//        }
//
//        return appointmentService.getAppointmentsByPatient(patientDB);
//    }

    /**
     * Appointment post request
     * @param appointmentDto
     * @return the saved appointment
     * @throws NotFoundException if the appointment targets are not in the database
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AppointmentDto postAppointment(@RequestBody AppointmentDto appointmentDto)
            throws NotFoundException
    {
        Doctor doctorDB = doctorService.getDoctor(appointmentDto.getDoctor_id());
        Patient patientDB = patientService.getPatient(appointmentDto.getPatient_id());

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointmentDto.getDoctor_id()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointmentDto.getPatient_id()));
        }

        Appointment appointment = AppointmentMapper.MAPPER.toAppointment(appointmentDto);
        appointment = appointmentService.saveAppointment(appointment);

        sendEmail(doctorDB, appointment.getId());
        sendEmail(patientDB, appointment.getId());
        return AppointmentMapper.MAPPER.fromAppoinment(appointment);
    }

//    /**
//     *
//     * @param id
//     * @param appointment
//     * @return
//     * @throws BadRequestException
//     * @throws NotFoundException
//     */
//    @PutMapping(value="/{id}")
//    public Appointment updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appointment) throws BadRequestException, NotFoundException
//    {
//        if(!id.equals(appointment.getAppointmentID()))
//        {
//            throw new BadRequestException("The id is not the same with id from object");
//        }
//
//        Appointment appointmentDb = appointmentService.getAppointment(id);
//        if(appointmentDb == null){
//            throw new NotFoundException(String.format("Appointment with id=%s was not found.", id));
//        }
//
//        Doctor doctorDb = doctorService.getDoctor(appointment.getDoctor().getDoctorID());
//        if(doctorDb == null){
//            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointment.getDoctor().getDoctorID()));
//        }
//
//        Patient patientDB = patientService.getPatient(appointment.getPatient().getPatientID());
//        if(patientDB == null){
//            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointment.getPatient().getPatientID()));
//        }
//
//        ObjectMapper.map2AppointmentDb(appointmentDb, appointment);
//
//        return appointmentService.saveAppointment(appointmentDb);
//    }
//
//    /**
//     *
//     * @param id
//     * @throws NotFoundException
//     */
//    @DeleteMapping(value="/{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteAppointment(@PathVariable Long id) throws NotFoundException
//    {
//        Appointment appointmentDb = appointmentService.getAppointment(id);
//        if(appointmentDb == null){
//            throw new NotFoundException(String.format("Appointment with id=%s was not found.", id));
//        }
//        appointmentService.deleteAppointment(appointmentDb);
//    }
//
//    /**
//     *
//     */
//    @DeleteMapping
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void deleteAllAppointments()
//    {
//        appointmentService.deleteAppointments();
//    }

    private void sendEmail(Person person, Long appointmentId)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(person.getEmail().getEmail());
            mimeMessageHelper.setSubject("Account created");
            mimeMessageHelper.setText(String.format("You can see your appointment at %s", "http://localhost:8080/api/0.1/appointments/" + appointmentId));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
