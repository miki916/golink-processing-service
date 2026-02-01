package com.asterisk.golink.application.service;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.service.AircraftService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAircraftEntityRepository;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AircraftEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAircraftService implements AircraftService {

    private final JpaAircraftEntityRepository repository;

    @Override
    public List<AircraftEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void updateFlightInfo(Aircraft aircraft) {

        repository.updateFlightInfo(aircraft.getVin(),
                aircraft.getAltitude(),
                aircraft.getSpeed(),
                aircraft.getStatus(),
                aircraft.getPosition(),
                aircraft.getOrientation());

    }
}
