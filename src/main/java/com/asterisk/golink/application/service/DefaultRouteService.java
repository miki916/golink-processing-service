package com.asterisk.golink.application.service;

import com.asterisk.golink.application.service.mapper.RouteMapper;
import com.asterisk.golink.domain.exception.DataNotFoundException;
import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.domain.model.modelEnum.RouteStatusEnum;
import com.asterisk.golink.domain.service.RouteService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaRouteEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultRouteService implements RouteService {

    private final JpaRouteEntityRepository repository;
    private final RouteMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<Route> findAll() {
        return mapper.toDomainList(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Route findByAircraftId(UUID vin) {
        return mapper.toDomain(repository.findByAircraftId(vin).orElseThrow(() -> new DataNotFoundException("Route not found for aircraft VIN: " + vin)));
    }

    @Override
    public void updateRouteToCompleted(Route route) {

        route.setStatus(RouteStatusEnum.COMPLETED);
        route.setArrivalTime(Timestamp.from(Instant.now()));

        Long durationInMin = (route.getArrivalTime().getTime() - route.getDepartureTime().getTime()) / 1000 / 60;

        route.setDuration(durationInMin.intValue());

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToLanding(Route route) {

        route.setStatus(RouteStatusEnum.LANDING);

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToDeparting(Route route) {

        route.setStatus(RouteStatusEnum.DEPARTING);

        if (route.getDepartureTime() == null) {
            route.setDepartureTime(Timestamp.from(Instant.now()));
        }

        this.repository.save(mapper.toEntity(route));

    }

    @Override
    public void updateRouteToInFlight(Route route) {

        route.setStatus(RouteStatusEnum.IN_FLIGHT);
        this.repository.save(mapper.toEntity(route));

    }
}
