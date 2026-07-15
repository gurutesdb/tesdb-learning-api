package com.tesdb.learning.student.service.impl;

import com.tesdb.learning.auth.service.EmailVerificationTokenService;
import com.tesdb.learning.core.enums.AuthProvider;
import com.tesdb.learning.core.exception.BadRequestException;
import com.tesdb.learning.core.exception.DuplicateResourceException;
import com.tesdb.learning.core.exception.ResourceNotFoundException;
import com.tesdb.learning.master.city.entity.City;
import com.tesdb.learning.master.city.repository.CityRepository;
import com.tesdb.learning.master.college.entity.College;
import com.tesdb.learning.master.college.repository.CollegeRepository;
import com.tesdb.learning.master.courseprogram.entity.CourseProgram;
import com.tesdb.learning.master.courseprogram.repository.CourseProgramRepository;
import com.tesdb.learning.master.department.entity.Department;
import com.tesdb.learning.master.department.repository.DepartmentRepository;
import com.tesdb.learning.student.dto.request.StudentRegisterRequest;
import com.tesdb.learning.student.dto.response.StudentResponse;
import com.tesdb.learning.student.entity.Student;
import com.tesdb.learning.student.mapper.StudentMapper;
import com.tesdb.learning.student.repository.StudentRepository;
import com.tesdb.learning.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService
{

    private final StudentRepository studentRepository;
    private final CourseProgramRepository courseProgramRepository;
    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;
    private final CityRepository cityRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationTokenService emailVerificationTokenService;

    @Override
    public StudentResponse register(StudentRegisterRequest request)
    {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        if (studentRepository.existsByRegisterNumber(request.getRegisterNumber())) {
            throw new DuplicateResourceException("Register number already exists.");
        }

        CourseProgram courseProgram = courseProgramRepository.findByUuid(request.getCourseProgramUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Course Program not found."));

        Department department = departmentRepository.findByUuidAndCourseProgramId(request.getDepartmentUuid(),courseProgram.getId())
                .orElseThrow(() -> new BadRequestException("Selected department does not belong to the selected course program."));

        City city = cityRepository.findByUuid(request.getCityUuid())
                .orElseThrow(() -> new ResourceNotFoundException("City not found."));

        College college = collegeRepository.findByUuidAndCityId(request.getCollegeUuid(), city.getId())
                .orElseThrow(() -> new BadRequestException("Selected college does not belong to the selected city."));

        Student student = new Student();

        student.setUuid(UUID.randomUUID());

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setMobile(request.getMobile());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setRegisterNumber(request.getRegisterNumber());
        student.setSemester(request.getSemester());
        student.setPassingOutYear(request.getPassingOutYear());
        student.setPercentage(request.getPercentage());
        student.setCourseProgram(courseProgram);
        student.setDepartment(department);
        student.setCollege(college);
        student.setCity(city);

        student.setEmailVerified(false);
        student.setAuthProvider(AuthProvider.LOCAL);

        student.setIsActive(true);
        student.setIsDeleted(false);

        Student savedStudent = studentRepository.save(student);

        emailVerificationTokenService.createVerificationToken(savedStudent);

        return studentMapper.toResponse(savedStudent);
    }
}
