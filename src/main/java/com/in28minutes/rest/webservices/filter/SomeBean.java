package com.in28minutes.rest.webservices.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonIgnoreProperties(value = {"field1"})
public class SomeBean {

    private String field1;
    private String field2;
    @JsonIgnore
    private String field3;

}
