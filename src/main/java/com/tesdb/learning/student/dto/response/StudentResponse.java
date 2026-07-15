package com.tesdb.learning.student.dto.response;

import com.tesdb.learning.core.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class StudentResponse
{
    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String registerNumber;

    private Short semester;

    private Integer passingOutYear;

    private BigDecimal percentage;

    private String courseProgram;

    private String department;

    private String college;

    private String city;

    private Boolean emailVerified;
}
