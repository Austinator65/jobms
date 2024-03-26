package com.example.jobms.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jobms.jobservice.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
