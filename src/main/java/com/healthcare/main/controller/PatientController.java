package com.healthcare.main.controller;

import com.healthcare.main.model.Patient;
import com.healthcare.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Patient getPatient(@PathVariable Long id)
    {
        return patientService.getPatient(id);
    }

    @GetMapping
    public List<Patient> getPatients()
    {
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient)
    {
        patientService.savePatient(patient);
        return patient;
    }

    @PutMapping(value="/{id}")
    public Patient updatePatient(@RequestBody Patient patient)
    {
        return patientService.updatePatient(patient);
    }

//    @PutMapping
//    public ResponseEntity<Object> updatePatients()
//    {
//        // doctorService.updatePatients(patientList);
//        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
//    }

    @DeleteMapping(value="/{id}")
    public Boolean deletePatient(@PathVariable Long id)
    {
        Patient patient = patientService.getPatient(id);
        return patientService.deletePatient(patient);
    }

    @DeleteMapping
    public Boolean deleteAllPatients()
    {
        return patientService.deleteAllPatients();
    }
}
