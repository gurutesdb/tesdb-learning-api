package com.tesdb.learning.student.service;

import com.tesdb.learning.student.dto.request.StudentRegisterRequest;
import com.tesdb.learning.student.dto.response.StudentResponse;

public interface StudentService
{
    StudentResponse register(StudentRegisterRequest request);
}
