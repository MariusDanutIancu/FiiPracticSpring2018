package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.AppointmentDto;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.boundry.mapper.AppointmentMapper;
import com.healthcare.main.control.service.*;
import com.healthcare.main.entity.model.*;
import com.healthcare.main.properties.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MessageService messageService;
    private CustomProperties customProps;

    //template used to add an hour to a java.util.Date object
    private static final long HOUR_IN_MILLISECONDS = 3600*1000;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService,
                                 PatientService patientService, EmailService emailService, MessageService messageService,
                                 CustomProperties customProps) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.emailService = emailService;
        this.messageService = messageService;
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
    @ResponseStatus(value = HttpStatus.OK)
    public AppointmentDto getAppointment(@PathVariable("id") Long id) throws NotFoundException
    {
        Appointment appointmentEntity = appointmentService.getAppointment(id);
        if(appointmentEntity == null)
        {
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("appointment.not.found.id"),
                            id
                    )
            );
        }

        return AppointmentMapper.MAPPER.toAppointmentDto(appointmentEntity);
    }

    /**
     * Appointment get request
     *
     * @return all appointments that are in the database
     */
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> getAllAppointments()
    {
        List<Appointment> appointmentListEntity = appointmentService.getAppointments();
        return AppointmentMapper.MAPPER.toAppointmentsDto(appointmentListEntity);
    }

    /**
     * Appointment get request with pagination
     *
     * @param pageable
     * @return an appointment list
     */
    @GetMapping(value="/filter")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> appointmentPageable(Pageable pageable)
    {
        Page<Appointment> appointmentsPageable = appointmentService.appointmentPageable(pageable);
        List<Appointment> appointmentList = new ArrayList<>();

        //TODO: refactor
        for(Appointment appointment: appointmentsPageable)
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
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> findAllByDoctorAndPatient(@RequestParam("patientid") Long patientid, @RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Doctor doctorEntity = doctorService.getDoctor(doctorid);
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            doctorid
                    )
            );
        }

        Patient patientEntity = patientService.getPatient(patientid);
        if(patientEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("patient.not.found.id"),
                            patientid
                    )
            );
        }

        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.getAppointmentsByDoctorAndPatient(doctorEntity, patientEntity)
        );
    }

    /**
     * Appointment get request using doctor id as filter
     *
     * @param doctorid unique doctor id
     * @return a list of appointments
     * @throws NotFoundException no appointments found
     */
    @GetMapping(value="/filter", params = "doctorid" )
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> findByDoctor(@RequestParam("doctorid") Long doctorid) throws NotFoundException
    {
        Doctor doctorEntity = doctorService.getDoctor(doctorid);
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            doctorid
                    )
            );
        }

        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.getAppointmentsByDoctor(doctorEntity)
        );
    }

    /**
     * Appointment get request using patient id as filter
     *
     * @param patientid unique patient id
     * @return a list of appointments
     * @throws NotFoundException no appointments found
     */
    @GetMapping(value="/filter", params = "patientid")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> findByPatient(@RequestParam("patientid") Long patientid) throws NotFoundException
    {
        Patient patientEntity = patientService.getPatient(patientid);
        if(patientEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("patient.not.found.id"),
                            patientid
                    )
            );
        }

        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.getAppointmentsByPatient(patientEntity)
        );
    }

    /**
     * Appointments get request using took place as filter.
     *
     * @param tookPlace boolean flag that marks if an appointments took place or not.
     * @return a list of appointments
     */
    @GetMapping(value="/filter", params = "took-place")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> findByTookPlace(@RequestParam("took-place") boolean tookPlace)
    {
        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.findAllByTookPlace(tookPlace)
        );
    }

    /**
     * Future appointments get request using doctorid as filter.
     * @param doctorid unique patient id
     * @return a list of appointments
     * @throws NotFoundException no appointments are found
     */
    @GetMapping(value="/future_appointments/filter", params = {"doctorid"})
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> findAllByDoctorAndEndTimeGreaterThan(@RequestParam("doctorid") Long doctorid)
            throws NotFoundException
    {
        Doctor doctorEntity = doctorService.getDoctor(doctorid);
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            doctorid
                    )
            );
        }

        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.findAllByDoctorAndEndTimeGreaterThan(doctorEntity, new Date())
        );
    }

    /**
     * Future appointments get request
     *
     * @return a list of appointments
     */
    @GetMapping(value="/future_appointments")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppointmentDto> getFutureAppointments()
    {
        return AppointmentMapper.MAPPER.toAppointmentsDto(
                appointmentService.findAllByEndTimeGreaterThan(new Date())
        );
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
        Doctor doctorEntity = doctorService.getDoctor(appointmentDto.getDoctor_id());
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            appointmentDto.getDoctor_id()
                    )
            );
        }

        Patient patientEntity = patientService.getPatient(appointmentDto.getPatient_id());
        if(patientEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("patient.not.found.id"),
                            appointmentDto.getPatient_id()
                    )
            );
        }

        if (appointmentDto.getStartTime().after(appointmentDto.getEndTime()))
        {
            throw new BadRequestException(
                    String.format(
                            messageService.getMessage("bad.request.date.start.after.end"),
                            appointmentDto.getStartTime(),
                            appointmentDto.getEndTime()
                    )
            );
        }

        Integer count = appointmentService.countAllByStartTimeBetweenAndDoctorOrPatient(
                appointmentDto.getStartTime(),
                appointmentDto.getEndTime(),
                doctorEntity,
                patientEntity);

        if (count > 0)
        {
            throw new BadRequestException(
                    String.format(
                            messageService.getMessage("appointment.bad.request.date.interval.booked.start.end"),
                            appointmentDto.getStartTime(),
                            appointmentDto.getEndTime()
                    )
            );
        }

        Appointment appointment = AppointmentMapper.MAPPER.toAppointment(doctorEntity, patientEntity, appointmentDto);
        appointment = appointmentService.saveAppointment(appointment);


        String message = String.format(
                messageService.getMessage("appointment.created.link"),
                customProps.getAppointmentsurl()
        ) + appointment.getId();

        emailService.sendEmailHttp(
                emailService.getEmail(
                        doctorEntity,
                        messageService.getMessage("appointment.set.subject"),
                        message
                )
        );

        emailService.sendEmailHttp(
                emailService.getEmail(
                        patientEntity,
                        messageService.getMessage("appointment.set.subject"),
                        message
                )
        );

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
    @ResponseStatus(value = HttpStatus.OK)
    public AppointmentDto putAppointment(@RequestBody AppointmentDto appointmentDto)
            throws NotFoundException, BadRequestException
    {
        Appointment appointmentEntity = appointmentService.getAppointment(appointmentDto.getAppointment_id());
        if(appointmentEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("appointment.not.found.id"),
                            appointmentDto.getAppointment_id()
                    )
            );
        }

        Doctor doctorEntity = doctorService.getDoctor(appointmentDto.getDoctor_id());
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            appointmentDto.getDoctor_id()
                    )
            );
        }

        Patient patientEntity = patientService.getPatient(appointmentDto.getPatient_id());
        if(patientEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("patient.not.found.id"),
                            appointmentDto.getPatient_id()
                    )
            );
        }

        if (appointmentEntity.getTookPlace())
        {
            throw new BadRequestException(
                    String.format(
                            messageService.getMessage("appointment.bad.request.took.place.id"),
                            appointmentDto.getAppointment_id()
                    )
            );
        }

        Date current_date = new Date();
        Date future_date =  new Date(current_date.getTime() + HOUR_IN_MILLISECONDS);
        if (appointmentEntity.getStartTime().before(future_date) && appointmentEntity.getStartTime().after(current_date))
        {
            throw new BadRequestException(
                    String.format(
                            messageService.getMessage("appointment.bad.request.next.hour.id"),
                            appointmentDto.getAppointment_id()
                    )
            );
        }

        AppointmentMapper.MAPPER.toAppointment(doctorEntity, patientEntity, appointmentDto, appointmentEntity);
        return AppointmentMapper.MAPPER.toAppointmentDto(
                appointmentService.saveAppointment(appointmentEntity)
        );
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
    @ResponseStatus(value = HttpStatus.OK)
    public AppointmentDto updateAppointment(@PathVariable("id") Long id, @RequestBody AppointmentDto appointmentDto)
            throws BadRequestException, NotFoundException
    {

        if (!id.equals(appointmentDto.getAppointment_id())) {
            throw new BadRequestException(
                    messageService.getMessage("bad.request.not.matching.id")
            );
        }

        if (appointmentDto.getCancel() != null)
        {
            throw new BadRequestException(
                    messageService.getMessage("appointment.bad.request.cant.cancel")
            );
        }

        Appointment appointmentEntity = appointmentService.getAppointment(appointmentDto.getAppointment_id());
        if(appointmentEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("appointment.not.found.id"),
                            appointmentDto.getAppointment_id()
                    )
            );
        }

        Doctor doctorEntity = doctorService.getDoctor(appointmentDto.getDoctor_id());
        if(doctorEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("doctor.not.found.id"),
                            appointmentDto.getDoctor_id()
                    )
            );
        }

        Patient patientEntity = patientService.getPatient(appointmentDto.getPatient_id());
        if(patientEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("patient.not.found.id"),
                            appointmentDto.getPatient_id()
                    )
            );
        }

        AppointmentMapper.MAPPER.toAppointment(doctorEntity, patientEntity, appointmentDto, appointmentEntity);
        return AppointmentMapper.MAPPER.toAppointmentDto(
                appointmentService.saveAppointment(appointmentEntity)
        );
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
        Appointment appointmentEntity = appointmentService.getAppointment(id);
        if(appointmentEntity == null){
            throw new NotFoundException(
                    String.format(
                            messageService.getMessage("appointment.not.found.id"),
                            id
                    )
            );
        }
        appointmentService.deleteAppointment(appointmentEntity);
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