package com.tesdb.learning.core.exception;

public class TokenExpiredException extends BadRequestException
{
    public TokenExpiredException(String message)
    {
        super(message);
    }

}
