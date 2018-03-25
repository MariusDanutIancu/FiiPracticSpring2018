package com.healthcare.main.entity.repository;


import com.healthcare.main.entity.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
