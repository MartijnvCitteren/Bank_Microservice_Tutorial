package com.example.accounts.exception;

import com.example.accounts.dto.ErrorResponseDto;
import com.example.accounts.exception.custom.CustomerAlreadyExists;
import com.example.accounts.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errorsMap = new HashMap<>();
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

        validationErrors.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMap.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(exception.getMessage())
                .errorTime(LocalDateTime.now()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomerAlreadyExists.class)
    public ResponseEntity<Object> customerAlreadyExists(CustomerAlreadyExists customerAlreadyExists, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(customerAlreadyExists.getMessage())
                .errorTime(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException resourceNotFoundException, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorMessage(resourceNotFoundException.getMessage())
                .errorTime(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }


}
