package com.crm.tehnomer.exceptions.customException;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(HttpStatus httpStatus, String message) {
    }
    private final HttpStatus httpStatus = null;
    private final String message = null;
}