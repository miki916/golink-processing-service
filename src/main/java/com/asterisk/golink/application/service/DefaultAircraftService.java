package com.asterisk.golink.application.service;

import com.asterisk.golink.application.service.mapper.AircraftMapper;
import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.service.AircraftService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAircraftEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAircraftService implements AircraftService {

    private final JpaAircraftEntityRepository repository;

    private final AircraftMapper mapper;

    @Override
    public List<Aircraft> findAll() {
        return mapper.toDomainList(repository.findAll());
    }

    @Transactional
    @Override
    public void updateFlightInfo(Aircraft aircraft) {

        repository.updateFlightInfo(aircraft.getVin(),
                aircraft.getAltitude(),
                aircraft.getSpeed(),
                aircraft.getFlightStatus().toString(),
                aircraft.getPosition(),
                aircraft.getOrientation());

    }
}
