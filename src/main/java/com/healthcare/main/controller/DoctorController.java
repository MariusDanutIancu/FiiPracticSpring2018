package com.healthcare.main.controller;

import com.healthcare.main.model.Doctor;
import com.healthcare.main.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Doctor getDoctor(@PathVariable Long id)
    {
        return doctorService.getDoctor(id);
    }

    @GetMapping
    public List<Doctor> getDoctors()
    {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor saveDoctor(@RequestBody Doctor doctor)
    {
        doctorService.saveDoctor(doctor);
        return doctor;
    }

    @PutMapping(value="/{id}")
    public Doctor updateDoctor(@RequestBody Doctor doctor)
    {
        return  doctorService.updateDoctor(doctor);
    }

//    @PutMapping
//    public ResponseEntity<Object> updateDoctors()
//    {
//        // doctorService.updateDoctors(doctor);
//        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
//    }

    @DeleteMapping(value="/{id}")
    public Boolean deleteDoctor(@PathVariable Long id)
    {
        Doctor doctor = doctorService.getDoctor(id);
        return doctorService.deleteDoctor(doctor);
    }

    @DeleteMapping
    public Boolean deleteAllDoctors()
    {
        return doctorService.deleteAllDoctors();
    }
}