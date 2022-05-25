package com.in28minutes.rest.webservices.user;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private Date birthDate;
}
