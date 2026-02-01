package com.asterisk.golink.infraestructure.controller;


import com.asterisk.golink.domain.service.AircraftService;
import com.asterisk.golink.infraestructure.controller.mapper.AircraftControllerMapper;
import com.asterisk.golink.infraestructure.controller.response.AircraftResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/v1/aircraft")
@RequiredArgsConstructor
@Slf4j
public class AircraftController {

    private final AircraftService service;

    private final AircraftControllerMapper mapper;

    @GetMapping()
    public List<AircraftResponse> getAllAircraft() {
        return mapper.toResponseList(service.findAll());
    }


}
