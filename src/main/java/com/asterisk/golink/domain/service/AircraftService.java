package com.asterisk.golink.domain.service;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AircraftEntity;

import java.util.List;

public interface AircraftService {

    List<Aircraft> findAll();

    void updateFlightInfo(Aircraft aircraft);
}
