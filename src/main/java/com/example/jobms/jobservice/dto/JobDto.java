package com.example.jobms.jobservice.dto;

import java.util.List;

import com.example.jobms.jobservice.model.external.Company;
import com.example.jobms.jobservice.model.external.Review;

import lombok.Data;


@Data
public class JobDto {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}

//test