package com.tesdb.learning.core.response;

public final class ResponseBuilder
{
    private ResponseBuilder()
    {

    }

    public static <T> SuccessResponse<T> success(
            String message,
            T data)
    {

        return SuccessResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }

    public static SuccessResponse<Void> success(
            String message)
    {

        return SuccessResponse.<Void>builder()
                .success(true)
                .message(message)
                .data(null)
                .build();

    }
}
