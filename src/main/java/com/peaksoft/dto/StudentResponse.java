package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private String name;
    private String surname;
    private int age;
    private Boolean isActive;
    private LocalDate created;
}
