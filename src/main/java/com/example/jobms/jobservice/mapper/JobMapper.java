package com.example.jobms.jobservice.mapper;

import java.util.List;

import com.example.jobms.jobservice.dto.JobDto;
import com.example.jobms.jobservice.model.Job;
import com.example.jobms.jobservice.model.external.Company;
import com.example.jobms.jobservice.model.external.Review;

public class JobMapper {
   public static  JobDto mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){

      JobDto jobWithCompanyDTO = new JobDto();
      jobWithCompanyDTO.setId(job.getId());
      jobWithCompanyDTO.setTitle(job.getTitle());
      jobWithCompanyDTO.setDescription(job.getDescription());
      jobWithCompanyDTO.setLocation(job.getLocation());
      jobWithCompanyDTO.setMinSalary(job.getMinSalary());
      jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
      jobWithCompanyDTO.setCompany(company);
      jobWithCompanyDTO.setReviews(reviews);

      return jobWithCompanyDTO;
   }
}
