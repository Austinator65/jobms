package com.example.jobms.jobservice.model.external;

import lombok.Data;

@Data
public class Company {
   // @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
