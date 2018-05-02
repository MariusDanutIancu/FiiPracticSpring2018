package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.AppointmentDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.AppointmentMapper;
import com.healthcare.main.control.service.AppointmentService;
import com.healthcare.main.control.service.DoctorService;
import com.healthcare.main.control.service.EmailService;
import com.healthcare.main.control.service.PatientService;
import com.healthcare.main.entity.model.*;
import com.healthcare.main.properties.CustomProperties;
import com.healthcare.main.util.email.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping(value="/api/0.1/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DoctorService doctorService;
    private PatientService patientService;
    private EmailService emailService;
    private MessageSource messageSource;
    private CustomProperties customProps;

    //template used to add an hour to a java.util.Date object
    private static final long HOUR_IN_MILLISECONDS = 3600*1000;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService,
                                 PatientService patientService, EmailService emailService, MessageSource messageSource,
                                 CustomProperties customProps) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.emailService = emailService;
        this.messageSource = messageSource;
        this.customProps = customProps;
    }

    /**
     * Appointment get request using appointment unique id.
     *
     * @param id appointment unique id
     * @return requested appointment
     * @throws NotFoundException no appointment with requested id found in database
     */
    @GetMapping(value="/{id}")
    public AppointmentDto getAppointment(@PathVariable("id") Long id) throws NotFoundException
    {
        Appointment appointmentDb = appointmentService.getAppointment(id);
        if(appointmentDb == null)
        {
            throw new NotFoundException(String.format("appointmentDb with id=%s was not found.", id));
        }

        return AppointmentMapper.MAPPER.toAppointmentDto(appointmentDb);
    }

    /**
     * Appointment get request
     *
     * @return all appointments that are in the database
     */
    @GetMapping()
    public List<AppointmentDto> getAllAppointments()
    {
        List<Appointment> appointmentListDb = appointmentService.getAppointments();
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointmentListDb);
    }

    /**
     * Appointment get request with pagination
     *
     * @param pageable
     * @return an appointment list
     */
    @GetMapping(value="/filter")
    public List<AppointmentDto> appointmentPageable(Pageable pageable)
    {
        Page<Appointment> appointments = appointmentService.appointmentPageable(pageable);
        List<Appointment> appointmentList = new ArrayList<>();

        //TODO: refactor
        for(Appointment appointment: appointments)
        {
            appointmentList.add(appointment);
        }

        return AppointmentMapper.MAPPER.toAppointmentsDto(appointmentList);
    }

    /**
     * Appointment get request using doctor id and patient id as filters
     *
     * @param patientid unique patient id
     * @param doctorid unique doctor id
     * @return a list of appointments
     * @throws NotFoundException no appointments found
     */
    @GetMapping(value="/filter", params = { "patientid", "doctorid" })
    public List<AppointmentDto> findAllByDoctorAndPatient(@RequestParam("patientid") Long patientid, @RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Doctor doctorDb = doctorService.getDoctor(doctorid);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
        }

        Patient patientDB = patientService.getPatient(patientid);
        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", patientid));
        }

        List<Appointment> appointments = appointmentService.getAppointmentsDoctorAndPatient(doctorDb, patientDB);
        return  AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Appointment get request using doctor id as filter
     *
     * @param doctorid unique doctor id
     * @return a list of appointments
     * @throws NotFoundException no appointments found
     */
    @GetMapping(value="/filter", params = "doctorid" )
    public List<AppointmentDto> findByDoctor(@RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Doctor doctorDb = doctorService.getDoctor(doctorid);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorDb);
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Appointment get request using patient id as filter
     *
     * @param patientid unique patient id
     * @return a list of appointments
     * @throws NotFoundException no appointments found
     */
    @GetMapping(value="/filter", params = "patientid")
    public List<AppointmentDto> findByPatient(@RequestParam("patientid") Long patientid) throws NotFoundException
    {
        Patient patientDB = patientService.getPatient(patientid);
        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", patientid));
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientDB);
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Appointments get request using took place as filter.
     *
     * @param tookPlace boolean flag that marks if an appointments took place or not.
     * @return a list of appointments
     */
    @GetMapping(value="/filter", params = "took-place")
    public List<AppointmentDto> findByTookPlace(@RequestParam("took-place") boolean tookPlace)
    {
        List<Appointment> appointments = appointmentService.findAllByTookPlace(tookPlace);
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Future appointments get request using doctorid as filter.
     * @param doctorid unique patient id
     * @return a list of appointments
     * @throws NotFoundException no appointments are found
     */
    @GetMapping(value="/future_appointments/filter", params = {"doctorid"})
    public List<AppointmentDto> findAllByDoctorAndEndTimeGreaterThan(@RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Date current_date = new Date();
        Doctor doctorDb = doctorService.getDoctor(doctorid);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", doctorid));
        }

        List<Appointment> appointments = appointmentService.findAllByDoctorAndEndTimeGreaterThan(doctorDb, current_date);
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Future appointments get request
     *
     * @return a list of appointments
     */
    @GetMapping(value="/future_appointments")
    public List<AppointmentDto> getFutureAppointments()
    {
        Date current_date = new Date();
        List<Appointment> appointments = appointmentService.findAllByEndTimeGreaterThan(current_date);
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointments);
    }

    /**
     * Appointment post request
     *
     * @param appointmentDto appointment data that needs to be saved
     * @return the saved appointment
     * @throws NotFoundException if the appointment targets are not in the database
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AppointmentDto postAppointment(@RequestBody AppointmentDto appointmentDto)
            throws NotFoundException, BadRequestException
    {
        Doctor doctorDB = doctorService.getDoctor(appointmentDto.getDoctor_id());
        Patient patientDB = patientService.getPatient(appointmentDto.getPatient_id());

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointmentDto.getDoctor_id()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointmentDto.getPatient_id()));
        }

        if (appointmentDto.getStartTime().after(appointmentDto.getEndTime()))
        {
            throw new BadRequestException(String.format("Start date is after end date %s %s.",
                    appointmentDto.getStartTime() , appointmentDto.getEndTime()));
        }

        Integer count = appointmentService.countAllByStartTimeBetweenAndDoctorOrPatient(appointmentDto.getStartTime(),
                appointmentDto.getEndTime(), doctorDB, patientDB);

        if (count > 0)
        {
            throw new BadRequestException(String.format("Interval is already booked %s %s",
                    appointmentDto.getStartTime() , appointmentDto.getEndTime()));
        }

        Appointment appointment = AppointmentMapper.MAPPER.toAppointment(doctorDB, patientDB, appointmentDto);
        appointment = appointmentService.saveAppointment(appointment);


        String message = String.format(messageSource.getMessage("appointment.created", null, Locale.getDefault()), customProps.getAppointmentsurl());
        message += appointment.getId();

        EmailUtil email = emailService.getEmail(doctorDB, "Appointment set", message);
        emailService.sendEmailHttp(email);

        email = emailService.getEmail(patientDB, "Appointment set", message);
        emailService.sendEmailHttp(email);

        return AppointmentMapper.MAPPER.toAppointmentDto(appointment);
    }

    /**
     * Appointment put request to cancel an appointment
     * @param appointmentDto appointment data that needs to be canceled
     * @return canceled appointment
     * @throws NotFoundException the appointment/doctor/patient was not found
     * @throws BadRequestException appointment took place or it will take place in the next hour
     */
    @PutMapping(value="/cancel_appointment")
    public AppointmentDto putAppointment(@RequestBody AppointmentDto appointmentDto)
            throws NotFoundException, BadRequestException
    {
        Appointment appointmentDb = appointmentService.getAppointment(appointmentDto.getAppointment_id());
        Doctor doctorDB = doctorService.getDoctor(appointmentDto.getDoctor_id());
        Patient patientDB = patientService.getPatient(appointmentDto.getPatient_id());

        if(appointmentDb == null){
            throw new NotFoundException(String.format("Appointment with id=%s was not found.", appointmentDto.getAppointment_id()));
        }

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointmentDto.getDoctor_id()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointmentDto.getPatient_id()));
        }

        if (appointmentDb.getTookPlace())
        {
            throw new BadRequestException(String.format("Appointment already took place %s", appointmentDto.getAppointment_id()));
        }

        Date current_date = new Date();
        Date future_date =  new Date(current_date.getTime() + HOUR_IN_MILLISECONDS);

        if (appointmentDb.getStartTime().before(future_date) && appointmentDb.getStartTime().after(current_date))
        {
            throw new BadRequestException(String.format("Appointments that occur in the next hour can't be canceled %s",
                    appointmentDto.getAppointment_id()));
        }

        AppointmentMapper.MAPPER.toAppointment(doctorDB, patientDB, appointmentDto, appointmentDb);
        appointmentDb = appointmentService.saveAppointment(appointmentDb);

        return AppointmentMapper.MAPPER.toAppointmentDto(appointmentDb);
    }

    /**
     *
     * @param id appointment unique id
     * @param appointmentDto appointment data that needs to be updated
     * @return updated appointment
     * @throws BadRequestException appointment path id does not match body id or appointment cancelation has been requested
     * @throws NotFoundException the appointment/doctor/patient was not found
     */
    @PutMapping(value="/{id}")
    public AppointmentDto updateAppointment(@PathVariable("id") Long id, @RequestBody AppointmentDto appointmentDto) throws BadRequestException, NotFoundException {
        if (!id.equals(appointmentDto.getAppointment_id())) {
            throw new BadRequestException("The id is not the same with id from object");
        }

        if (appointmentDto.getCancel() != null)
        {
            throw new BadRequestException("You can't cancel an appointment through this request");
        }

        Appointment appointmentDb = appointmentService.getAppointment(appointmentDto.getAppointment_id());
        Doctor doctorDB = doctorService.getDoctor(appointmentDto.getDoctor_id());
        Patient patientDB = patientService.getPatient(appointmentDto.getPatient_id());

        if(appointmentDb == null){
            throw new NotFoundException(String.format("Appointment with id=%s was not found.", appointmentDto.getAppointment_id()));
        }

        if(doctorDB == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", appointmentDto.getDoctor_id()));
        }

        if(patientDB == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", appointmentDto.getPatient_id()));
        }


        AppointmentMapper.MAPPER.toAppointment(doctorDB, patientDB, appointmentDto, appointmentDb);
        appointmentDb = appointmentService.saveAppointment(appointmentDb);

        return AppointmentMapper.MAPPER.toAppointmentDto(appointmentDb);
    }

    /**
     *
     * @param id appointment unique id
     * @throws NotFoundException the requested appointment was not found
     */
    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) throws NotFoundException
    {
        Appointment appointmentDb = appointmentService.getAppointment(id);
        if(appointmentDb == null){
            throw new NotFoundException(String.format("Appointment with id=%s was not found.", id));
        }
        appointmentService.deleteAppointment(appointmentDb);
    }

    /**
     * Deletes all appointments
     */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllAppointments()
    {
        appointmentService.deleteAppointments();
    }
}
