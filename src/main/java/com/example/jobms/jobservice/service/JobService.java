package com.example.jobms.jobservice.service;

import java.util.List;

import com.example.jobms.jobservice.dto.JobDto;
import com.example.jobms.jobservice.model.Job;


public interface JobService {
   List<JobDto> findAll();
   void createJob(Job job);
   JobDto getJobById(Long id);
   boolean deleteJob(Long id);
   boolean updateJob(Long id, Job job);
}
