package com.example.productservice.advices;


import com.example.productservice.dtos.ResponseDto;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.exceptions.*;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.crypto.spec.PSource;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvices {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setStatus(ResponseStatus.BAD_REQUEST);
        responseDto.setMessage("Validation failed.");
        responseDto.setData(null);
        responseDto.setValidationErrors(errors);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(responseDto);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ResponseDto<Object>> handleProductAlreadyExistsException(ProductAlreadyExistsException e){
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setStatus(ResponseStatus.BAD_REQUEST);
        responseDto.setMessage(e.getMessage());
        responseDto.setData(null);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(responseDto);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDto<Object>> handleProductNotFoundException(ProductNotFoundException e){
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setStatus(ResponseStatus.NOT_FOUND);
        responseDto.setMessage(e.getMessage());
        responseDto.setData(null);
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(responseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException e){

        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setStatus(ResponseStatus.FAILURE);
        responseDto.setMessage(e.getMessage());
        responseDto.setData(null);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(responseDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Object>> handleException(Exception e){
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setStatus(ResponseStatus.FAILURE);
        responseDto.setMessage(e.getMessage());
        responseDto.setData(null);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(responseDto);
    }
}
