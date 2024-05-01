package com.felix.loginapp.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResponseBody<T> {
    private final String message;
    private final T data;
}
