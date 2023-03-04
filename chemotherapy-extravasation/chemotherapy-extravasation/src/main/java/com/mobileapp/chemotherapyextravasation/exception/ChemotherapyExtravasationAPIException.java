package com.mobileapp.chemotherapyextravasation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ChemotherapyExtravasationAPIException extends RuntimeException{
    private String message;

    public ChemotherapyExtravasationAPIException(String message) {
        super(message);
    }
}