package com.example.jobms.jobservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobms.jobservice.dto.JobDto;
import com.example.jobms.jobservice.model.Job;
import com.example.jobms.jobservice.service.JobService;


@RestController
@RequestMapping("/app/jobs")
public class JobController {
   
   @Autowired
   private JobService jobService;

    //Get and Post all Jobs
    @GetMapping
    public List<JobDto> findAll(){
        return jobService.findAll();
    }

    @PostMapping
    public String createJob(@RequestBody Job job){
         jobService.createJob(job);
         return "Job created Successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id){
        JobDto job = jobService.getJobById(id);
        if(job != null)
          return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean deleted = jobService.deleteJob(id);
        if(deleted){
           // jobService.deleteJob(id);
           return new ResponseEntity<>("Job deleted Succesfully",HttpStatus.OK);
        }
           
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return new ResponseEntity<>("Job updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}               
