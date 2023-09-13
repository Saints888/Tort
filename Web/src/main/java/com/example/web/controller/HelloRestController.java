package com.example.web.controller;


import com.example.web.service.TortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value = "/api/tort", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloRestController {

    private TortService tortService;

    @Autowired
    public void setTortService(TortService tortService) {this.tortService = tortService}
}
