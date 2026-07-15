package com.tesdb.learning.student.mapper;

import com.tesdb.learning.student.dto.response.StudentResponse;
import com.tesdb.learning.student.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper
{
    public StudentResponse toResponse(Student student) {

        StudentResponse response = new StudentResponse();

        response.setUuid(student.getUuid());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setMobile(student.getMobile());
        response.setGender(student.getGender());
        response.setDateOfBirth(student.getDateOfBirth());
        response.setRegisterNumber(student.getRegisterNumber());
        response.setSemester(student.getSemester());
        response.setPassingOutYear(student.getPassingOutYear());
        response.setPercentage(student.getPercentage());

        response.setCourseProgram(student.getCourseProgram().getName());
        response.setDepartment(student.getDepartment().getName());
        response.setCollege(student.getCollege().getName());
        response.setCity(student.getCity().getName());

        response.setEmailVerified(student.getEmailVerified());

        return response;
    }
}

