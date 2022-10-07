package com.example.server.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {


    protected LocalDateTime timestamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected  String developperMessage;
    protected Map<?,?> data;

}
