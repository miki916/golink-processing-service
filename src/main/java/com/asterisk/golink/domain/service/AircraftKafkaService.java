package com.asterisk.golink.domain.service;

import com.asterisk.golink.domain.model.Aircraft;

public interface AircraftKafkaService {

    void receiveAircraftData(Aircraft aircraft);

}
