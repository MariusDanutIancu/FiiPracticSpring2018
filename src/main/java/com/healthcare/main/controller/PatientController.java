package com.healthcare.main.controller;

import com.healthcare.main.exception.BadRequestException;
import com.healthcare.main.exception.NotFoundException;
import com.healthcare.main.mapper.ObjectMapper;
import com.healthcare.main.model.Doctor;
import com.healthcare.main.model.Patient;
import com.healthcare.main.model.PatientBatch;
import com.healthcare.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/0.1/patients")
public class PatientController
{
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value="/{id}")
    public Patient getPatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);
        if(patient == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }
        return patientService.getPatient(id);
    }

    @GetMapping
    public List<Patient> getPatients()
    {
        return patientService.getAllPatients();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Patient savePatient(@RequestBody Patient patient)
    {
        patientService.savePatient(patient);
        return patient;
    }

    @PutMapping(value="/{id}")
    public Patient updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) throws BadRequestException, NotFoundException
    {
        if(!id.equals(patient.getPatientID()))
        {
            throw new BadRequestException("The id is not the same with id from object");
        }

        Patient patientDb = patientService.getPatient(id);

        if(patientDb == null)
        {
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }

        ObjectMapper.map2PatientDb(patientDb, patient);
        return patientService.updatePatient(patientDb);
    }

    /**
     * Function is not validated!
     *
     * @param patientBatch
     * @return
     * @throws NotFoundException
     */
    @PutMapping()
    public PatientBatch updatePatients(@RequestBody PatientBatch patientBatch) throws NotFoundException
    {
        int batchSize = patientBatch.getPatientList().size();
        List<Patient> mappedData = new ArrayList<>();

        // No changes are made if an error is found
        // Map necessary data
        for (int i = 0; i < batchSize; i++) {

            Long id = patientBatch.getPatientList().get(i).getPatientID();
            Patient patient = patientBatch.getPatientList().get(i); // request op
            Patient patientDb = patientService.getPatient(id); // database op

            if(patientDb == null){
                throw new NotFoundException(String.format("Patient with id=%s was not found. Batch not updated", id));
            }

            ObjectMapper.map2PatientDb(patientDb, patient);
            mappedData.add(patientDb);
        }

        List<Patient> updatedData = patientService.updatePatients(mappedData);
        patientBatch.setPatientList(updatedData);
        return patientBatch;
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("id") Long id) throws NotFoundException
    {
        Patient patient = patientService.getPatient(id);
        if(patient == null){
            throw new NotFoundException(String.format("Patient with id=%s was not found.", id));
        }
        patientService.deletePatient(patient);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllPatients()
    {
        patientService.deleteAllPatients();
    }
}
