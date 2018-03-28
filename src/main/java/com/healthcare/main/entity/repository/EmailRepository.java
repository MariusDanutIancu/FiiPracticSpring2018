package com.healthcare.main.entity.repository;

import com.healthcare.main.entity.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long>
{

}