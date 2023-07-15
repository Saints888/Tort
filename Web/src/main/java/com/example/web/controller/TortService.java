package com.example.web.controller;

import java.util.List;

public interface TortService {
    Tort getTortById(Long id);
    List <Tort> getAllTorts();
    void addTort(Tort tort);
}
