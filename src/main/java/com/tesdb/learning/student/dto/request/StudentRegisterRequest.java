package com.tesdb.learning.student.dto.request;

import com.tesdb.learning.core.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class StudentRegisterRequest
{
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String mobile;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    private String registerNumber;

    @NotNull
    @Min(1)
    @Max(12)
    private Short semester;

    @NotNull
    private Integer passingOutYear;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("100.00")
    private BigDecimal percentage;

    @NotNull
    private UUID courseProgramUuid;

    @NotNull
    private UUID departmentUuid;

    @NotNull
    private UUID collegeUuid;

    @NotNull
    private UUID cityUuid;
}
