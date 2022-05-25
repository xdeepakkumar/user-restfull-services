package com.in28minutes.rest.webservices.user.exception;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

}
