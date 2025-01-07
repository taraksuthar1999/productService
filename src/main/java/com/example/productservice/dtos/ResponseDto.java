package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ResponseDto<T> {
    private ResponseStatus status;
    private String message;
    private T data;
    private Map<String,String> validationErrors;

    public ResponseDto(){}
    public ResponseDto(ResponseStatus status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDto(ResponseStatus status, String message, T data, Map<String,String> validationErrors){
        this.status = status;
        this.message = message;
        this.data = data;
        this.validationErrors = validationErrors;
    }
}
