package com.healthcare.main.entity.repository;

import com.healthcare.main.entity.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
