package com.tesdb.learning.auth.service;

import com.tesdb.learning.auth.entity.EmailVerificationToken;
import com.tesdb.learning.student.entity.Student;

public interface EmailVerificationTokenService
{
    EmailVerificationToken createVerificationToken(Student student);

    EmailVerificationToken verifyToken(String token);
}
