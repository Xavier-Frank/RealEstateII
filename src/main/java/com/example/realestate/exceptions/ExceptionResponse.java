package com.example.realestate.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Builder
@Data
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private List<?> details;
}
