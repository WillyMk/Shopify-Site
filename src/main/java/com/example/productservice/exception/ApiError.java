package com.example.productservice.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    @JsonIgnore
    private HttpStatus statusCode;
    private String message;
    private Date timeStamp;
    private String code;

    public ApiError(HttpStatus httpStatus, String format) {
        this.statusCode= httpStatus;
        this.code= String.valueOf(httpStatus.value());
        this.setMessage(format);
    }
}
