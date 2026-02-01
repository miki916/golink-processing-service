package com.asterisk.golink.infraestructure.controller;


import com.asterisk.golink.domain.service.AirfieldService;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/v1/airfield")
@RequiredArgsConstructor
@Slf4j
public class AirfieldController {

    private final AirfieldService service;

    @GetMapping()
    public List<AirfieldEntity> getAllAirfield() {
        return service.findAll();
    }


}
