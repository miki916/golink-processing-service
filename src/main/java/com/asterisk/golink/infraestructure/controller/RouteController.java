package com.asterisk.golink.infraestructure.controller;


import com.asterisk.golink.domain.service.RouteService;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
@Slf4j
public class RouteController {

    private final RouteService service;

    @GetMapping()
    public List<RouteEntity> getAllRoute() {
        return service.findAll();
    }


}
