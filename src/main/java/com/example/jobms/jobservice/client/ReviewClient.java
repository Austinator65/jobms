package com.example.jobms.jobservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jobms.jobservice.model.external.Review;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {
   
  // http://REVIEW-SERVICE:8083/app/reviews?companyId=
   @GetMapping("/app/reviews")
   List<Review> getReviews(@RequestParam("companyId") Long id);
}
