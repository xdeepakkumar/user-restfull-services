package com.in28minutes.rest.webservices.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveBean(){
        return new SomeBean("value1", "Value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retrieveListOfBean(){
        return Collections.singletonList(new SomeBean("value1", "Value2", "value3"));
    }
}
