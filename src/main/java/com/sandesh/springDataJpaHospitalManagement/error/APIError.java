package com.sandesh.springDataJpaHospitalManagement.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIError {
    private LocalDateTime dateTime;
    private String message;
    private HttpStatus status;

    public APIError(String message, HttpStatus status) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
        this.status = status;
    }

}
