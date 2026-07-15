package com.tesdb.learning.core.exception;

public class BadRequestException extends ApiException
{
    public BadRequestException(String message)
    {
        super(message);
    }
}
