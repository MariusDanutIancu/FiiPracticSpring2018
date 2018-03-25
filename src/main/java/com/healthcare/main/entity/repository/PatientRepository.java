package com.healthcare.main.entity.repository;

import com.healthcare.main.entity.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>
{

}
