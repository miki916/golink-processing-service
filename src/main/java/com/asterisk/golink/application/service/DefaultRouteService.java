package com.asterisk.golink.application.service;

import com.asterisk.golink.application.service.mapper.RouteMapper;
import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.domain.service.RouteService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaRouteEntityRepository;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultRouteService implements RouteService {

    private final JpaRouteEntityRepository repository;
    private final RouteMapper mapper;

    @Override
    public List<RouteEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Route findByAircraftId(UUID vin) {
        return mapper.toDomain(repository.findByAircraftId(vin));
    }

    @Override
    public void updateRouteToCompleted(Route route) {

        route.setStatus("COMPLETED");
        route.setArrivalTime(Timestamp.from(Instant.now()));

        Long durationInMin = (route.getArrivalTime().getTime() - route.getDepartureTime().getTime()) / 1000 / 60;

        route.setDuration(durationInMin.intValue());

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToLanding(Route route) {

        route.setStatus("LANDING");

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToDeparting(Route route) {

        route.setStatus("DEPARTING");

        if (route.getDepartureTime() == null) {
            route.setDepartureTime(Timestamp.from(Instant.now()));
        }

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToInFlight(Route route) {

        route.setStatus("IN_FLIGHT");
        this.repository.save(mapper.toEntity(route));

    }
}
