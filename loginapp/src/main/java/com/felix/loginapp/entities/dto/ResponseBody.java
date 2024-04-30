package com.felix.loginapp.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseBody<T> {
    private String message;
    private T data;
}
