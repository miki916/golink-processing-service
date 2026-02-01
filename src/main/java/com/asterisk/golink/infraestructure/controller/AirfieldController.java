package com.asterisk.golink.infraestructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asterisk.golink.domain.service.AirfieldService;
import com.asterisk.golink.infraestructure.controller.mapper.AirfieldControllerMapper;
import com.asterisk.golink.infraestructure.controller.response.AirfieldResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController

@RequestMapping("/api/v1/airfield")
@RequiredArgsConstructor
@Slf4j
public class AirfieldController {

  private final AirfieldService service;

  private final AirfieldControllerMapper mapper;

  @GetMapping()
  public List<AirfieldResponse> getAllAirfield() {

    return mapper.toResponseList(service.findAll());
  }

}
