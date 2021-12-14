package com.example.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
