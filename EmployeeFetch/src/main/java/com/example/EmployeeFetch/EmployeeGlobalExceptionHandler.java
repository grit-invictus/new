package com.example.EmployeeFetch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler({EmployeeAlreadyExist.class})
    public ResponseEntity<String> EmployeeDoesnotExistHandler(EmployeeAlreadyExist employeeexist) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employeeexist.getMessage());


    }

    @ExceptionHandler({EmployeeDoesnotExist.class})
    public ResponseEntity<String> EmployeeDoesnotExistHandler(EmployeeDoesnotExist employeedoesnotexist) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(employeedoesnotexist.getMessage());


    }

}
    class EmployeeAlreadyExist extends RuntimeException {
        String message;

        EmployeeAlreadyExist(String message) {
            super(message);
        }

    }

    class EmployeeDoesnotExist extends RuntimeException {

        String message;

        EmployeeDoesnotExist(String message) {
            super(message);
        }
    }



