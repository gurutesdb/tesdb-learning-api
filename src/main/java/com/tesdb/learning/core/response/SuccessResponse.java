package com.tesdb.learning.core.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponse<T>
{
    private boolean success;

    private String message;

    private T data;
}
