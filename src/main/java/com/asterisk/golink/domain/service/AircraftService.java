package com.asterisk.golink.domain.service;

import java.util.List;

import com.asterisk.golink.domain.model.Aircraft;

public interface AircraftService {

  List<Aircraft> findAll();

  void updateFlightInfo(Aircraft aircraft);
}
