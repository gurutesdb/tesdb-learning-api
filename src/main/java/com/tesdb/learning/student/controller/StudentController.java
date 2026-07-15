package com.tesdb.learning.student.controller;

import com.tesdb.learning.core.response.ApiResponse;
import com.tesdb.learning.core.response.ResponseUtil;
import com.tesdb.learning.student.dto.request.StudentRegisterRequest;
import com.tesdb.learning.student.dto.response.StudentResponse;
import com.tesdb.learning.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController
{
    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<StudentResponse>> register(@Valid @RequestBody StudentRegisterRequest request)
    {
        StudentResponse response = studentService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtil.success("Student registered successfully.", response));
    }
}
