package com.asterisk.golink.domain.service;

import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;

import java.util.List;
import java.util.UUID;

public interface RouteService {

    List<RouteEntity> findAll();

    Route findByAircraftId(UUID vin);

    void updateRouteToCompleted(Route route);

    void updateRouteToLanding(Route route);


    void updateRouteToDeparting(Route route);

    void updateRouteToInFlight(Route route);
}
