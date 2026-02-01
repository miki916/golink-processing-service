package com.asterisk.golink.application.service;

import org.springframework.stereotype.Service;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.domain.model.modelEnum.FlightStatusEnum;
import com.asterisk.golink.domain.model.modelEnum.RouteStatusEnum;
import com.asterisk.golink.domain.service.AircraftInjectorService;
import com.asterisk.golink.domain.service.AircraftService;
import com.asterisk.golink.domain.service.RouteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAircraftInjectorService implements AircraftInjectorService {

  public static final String RECEIVING_AIRCRAFT_DATA_FOR_AIRCRAFT_VIN =
      "[AIRCRAFT_INJECTOR_SERVICE] Receiving aircraft data for aircraft VIN: {}";
  public static final String AIRCRAFT_VIN_HAS_LANDED =
      "[AIRCRAFT_INJECTOR_SERVICE] Aircraft VIN: {} has landed";
  public static final String AIRCRAFT_VIN_IS_LANDING =
      "[AIRCRAFT_INJECTOR_SERVICE] Aircraft VIN: {} is landing";
  public static final String AT_ORIGIN_AIRFIELD =
      "[AIRCRAFT_INJECTOR_SERVICE] Aircraft VIN: {} still on ground at origin airfield";
  public static final String IS_DEPARTING =
      "[AIRCRAFT_INJECTOR_SERVICE] Aircraft VIN: {} is departing";
  public static final String IN_FLIGHT =
      "[AIRCRAFT_INJECTOR_SERVICE] Aircraft VIN: {} is now in flight";

  private final AircraftService aircraftService;
  private final RouteService routeService;

  @Override
  public void receiveAircraftData(Aircraft aircraft) {

    log.info(RECEIVING_AIRCRAFT_DATA_FOR_AIRCRAFT_VIN, aircraft.getVin());

    Route route = routeService.findByAircraftId(aircraft.getVin());

    Boolean isLanding =
        this.checkIfIsInTheAirfield(aircraft, route.getDestination());

    if (isLanding) {
      if (aircraft.getAltitude() <= 0) {
        log.info(AIRCRAFT_VIN_HAS_LANDED, aircraft.getVin());

        aircraft.setFlightStatus(FlightStatusEnum.ON_GROUND);
        routeService.updateRouteToCompleted(route);
      } else {
        log.info(AIRCRAFT_VIN_IS_LANDING, aircraft.getVin());

        aircraft.setFlightStatus(FlightStatusEnum.AIRBORNE);
        routeService.updateRouteToLanding(route);
      }

      aircraftService.updateFlightInfo(aircraft);
      return;
    }

    Boolean isDeparting =
        this.checkIfIsInTheAirfield(aircraft, route.getOrigin());

    if (isDeparting) {
      log.info(IS_DEPARTING, aircraft.getVin());

      if (aircraft.getAltitude() <= 0) {
        log.info(AT_ORIGIN_AIRFIELD, aircraft.getVin());
        aircraft.setFlightStatus(FlightStatusEnum.ON_GROUND);
      } else {
        aircraft.setFlightStatus(FlightStatusEnum.AIRBORNE);
      }

      routeService.updateRouteToDeparting(route);
      aircraftService.updateFlightInfo(aircraft);
      return;
    }

    aircraft.setFlightStatus(FlightStatusEnum.AIRBORNE);

    if (!route.getStatus().equals(RouteStatusEnum.IN_FLIGHT)) {
      log.info(IN_FLIGHT, aircraft.getVin());

      routeService.updateRouteToInFlight(route);
    }

    aircraftService.updateFlightInfo(aircraft);
  }

  private Boolean checkIfIsInTheAirfield(Aircraft aircraft,
      Airfield destination) {

    return destination.getLocation().contains(aircraft.getPosition());
  }

}
