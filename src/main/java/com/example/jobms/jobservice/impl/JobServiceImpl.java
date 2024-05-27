package com.example.jobms.jobservice.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobms.jobservice.client.CompanyClient;
import com.example.jobms.jobservice.client.ReviewClient;
import com.example.jobms.jobservice.dto.JobDto;
import com.example.jobms.jobservice.mapper.JobMapper;
import com.example.jobms.jobservice.model.Job;
import com.example.jobms.jobservice.model.external.Company;
import com.example.jobms.jobservice.model.external.Review;
import com.example.jobms.jobservice.repository.JobRepository;
import com.example.jobms.jobservice.service.JobService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class JobServiceImpl implements JobService {

   @Autowired
   private JobRepository jobRepository;

   // @Autowired
   // private RestTemplate restTemplate;

   @Autowired
   private CompanyClient companyClient;

   @Autowired
   private ReviewClient reviewClient;

   @Override
   @CircuitBreaker(name = "companyBreaker")
   public List<JobDto> findAll() {

      List<Job> jobs = jobRepository.findAll();

      return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

   }

   private JobDto convertToDto(Job job) {

      // Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/app/companies/" + job.getCompanyId(),
      //       Company.class);

      Company company = companyClient.getCompany(job.getCompanyId());

      // ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
      //       "http://REVIEW-SERVICE:8083/app/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null,
      //       new ParameterizedTypeReference<List<Review>>() {
      //       });

     

      List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

      JobDto jobDto = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);

      jobDto.setCompany(company);
      jobDto.setReviews(reviews);

      return jobDto;

   }

   @Override
   public void createJob(Job job) {
      jobRepository.save(job);
   }

   @Override
   public JobDto getJobById(Long id) {
      Job job = jobRepository.findById(id).orElse(null);
      JobDto jobWithCompanyDTO = convertToDto(job);

      return jobWithCompanyDTO;
   }

   @Override
   public boolean deleteJob(Long id) {
      if (jobRepository.findById(id).isPresent()) {
         jobRepository.deleteById(id);
         return true;
      }
      return false;
   }

   @Override
   public boolean updateJob(Long id, Job updatedJob) {

      Optional<Job> optJob = jobRepository.findById(id);
      if (optJob.isPresent()) {
         Job job = optJob.get();
         job.setTitle(updatedJob.getTitle());
         job.setDescription(updatedJob.getDescription());
         job.setLocation(updatedJob.getLocation());
         job.setMinSalary(updatedJob.getMinSalary());
         job.setMaxSalary(updatedJob.getMaxSalary());
         jobRepository.save(job);
         return true;
      }
      return false;
   }

}
