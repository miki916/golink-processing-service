package com.asterisk.golink.domain.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.model.Route;

public interface RouteService {

  List<Route> findAll();

  Route findByAircraftId(UUID vin);

  void updateRouteToCompleted(Route route);

  void updateRouteToLanding(Route route);

  void updateRouteToDeparting(Route route);

  void updateRouteToInFlight(Route route);

}
