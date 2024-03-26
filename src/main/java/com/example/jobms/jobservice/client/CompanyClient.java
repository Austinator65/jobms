package com.example.jobms.jobservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.jobms.jobservice.model.external.Company;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {

   @GetMapping("/app/companies/{id}")
   Company getCompany(@PathVariable("id") Long id);
}
