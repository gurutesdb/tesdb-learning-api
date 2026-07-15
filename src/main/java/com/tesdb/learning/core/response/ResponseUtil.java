package com.tesdb.learning.core.response;

public final class ResponseUtil
{
    private ResponseUtil() {}

    public static <T> ApiResponse<T> success(String message, T data)
    {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data)
    {
        return success("Success", data);
    }

    public static ApiResponse<Object> success(String message)
    {
        return success(message, null);
    }
}
