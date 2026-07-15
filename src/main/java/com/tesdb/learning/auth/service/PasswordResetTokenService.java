package com.tesdb.learning.auth.service;

import com.tesdb.learning.auth.entity.PasswordResetToken;
import com.tesdb.learning.student.entity.Student;

public interface PasswordResetTokenService
{
    PasswordResetToken createToken(Student student);

    PasswordResetToken validateToken(String token);

    void markTokenAsUsed(PasswordResetToken passwordResetToken);
}
