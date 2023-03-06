package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate createdDate;
    private String roleName;
}
