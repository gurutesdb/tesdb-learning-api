package com.tesdb.learning.core.exception;

public class UnauthorizedException extends ApiException
{
    public UnauthorizedException(String message)
    {
        super(message);
    }
}
