package com.tesdb.learning.core.exception;

public class EmailNotVerifiedException extends UnauthorizedException
{
    public EmailNotVerifiedException(String message)
    {
        super(message);
    }
}
