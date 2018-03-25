package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.exception.MethodNotAllowedException;
import com.healthcare.main.boundry.mapper.ObjectMapper;
import com.healthcare.main.boundry.exception.BadRequestException;
import com.healthcare.main.boundry.exception.NotFoundException;
import com.healthcare.main.entity.model.Doctor;
import com.healthcare.main.entity.model.DoctorBatch;
import com.healthcare.main.control.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/0.1/doctors")
public class DoctorController
{
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(value="/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) throws NotFoundException
    {
        Doctor doctor = doctorService.getDoctor(id);
        if(doctor == null)
        {
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }
        return doctor;
    }

    @GetMapping
    public List<Doctor> getDoctors()
    {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Doctor saveDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.saveDoctor(doctor);
    }

    @PostMapping(value="/{id}")
    public Doctor saveDoctor_not_allowed(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws MethodNotAllowedException
    {
        throw new MethodNotAllowedException("Method is not allowed.");
    }

    @PutMapping(value="/{id}")
    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor) throws BadRequestException, NotFoundException
    {
        if(!id.equals(doctor.getDoctorID())){
            throw new BadRequestException("The id is not the same with id from object");
        }

        Doctor doctorDb = doctorService.getDoctor(id);
        if(doctorDb == null){
            throw new NotFoundException(String.format("Doctor with id=%s was not found.", id));
        }

        ObjectMapper.map2DoctorDb(doctorDb, doctor);
        return  doctorService.updateDoctor(doctorDb);
    }

    /**
     * Function is not validated!
     *
     * @param doctorBatch
     * @return
     * @throws NotFoundException
     */
    @PutMapping()
    public DoctorBatch updateDoctors(@RequestBody DoctorBatch doctorBatch) throws NotFoundException
    {
        int batchSize = doctorBatch.getDoctorList().size();
        List<Doctor> mappedData = new ArrayList<>();

        // No changes are made if an error is found
        // Map necessary data
        for (int i = 0; i < batchSize; i++) {

            Long id = doctorBatch.getDoctorList().get(i).getDoctorID();
            Doctor doctor = doctorBatch.getDoctorList().get(i); // request op
            Doctor doctorDb = doctorService.getDoctor(id); // repository op

            if(doctorDb == null){
                throw new NotFoundException(String.format("Doctor with id=%s was not found. Batch not updated", id));
            }

            ObjectMapper.map2DoctorDb(doctorDb, doctor);
            mappedData.add(doctorDb);
        }

        List<Doctor> updatedData = doctorService.updateDoctors(mappedData);
        doctorBatch.setDoctorList(updatedData);
        return doctorBatch;
    }

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

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllDoctors()
    {
        doctorService.deleteAllDoctors();
    }
}