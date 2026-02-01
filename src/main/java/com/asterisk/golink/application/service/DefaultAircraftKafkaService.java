package com.asterisk.golink.application.service;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.domain.service.AircraftKafkaService;
import com.asterisk.golink.domain.service.AircraftService;
import com.asterisk.golink.domain.service.AirfieldService;
import com.asterisk.golink.domain.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DefaultAircraftKafkaService implements AircraftKafkaService {

    private final AircraftService aircraftService;
    private final AirfieldService airfieldService;
    private final RouteService routeService;

    @Override
    public void receiveAircraftData(Aircraft aircraft) {

        Route route = routeService.findByAircraftId(aircraft.getVin());

        Boolean isLanding = this.checkIfIsInTheAirfield(aircraft, route.getDestination());

        if (isLanding) {

            if (aircraft.getAltitude() <= 0) {

                aircraft.setStatus("ON_GROUND");
                routeService.updateRouteToCompleted(route);


            } else {

                aircraft.setStatus("AIRBORNE");
                routeService.updateRouteToLanding(route);

            }

            aircraftService.updateFlightInfo(aircraft);
            return;

        }

        Boolean isDeparting = this.checkIfIsInTheAirfield(aircraft, route.getOrigin());

        if (isDeparting) {

            if (aircraft.getAltitude() <= 0) {
                aircraft.setStatus("ON_GROUND");
            } else {
                aircraft.setStatus("AIRBORNE");
            }

            routeService.updateRouteToDeparting(route);
            aircraftService.updateFlightInfo(aircraft);
            return;

        }

        aircraft.setStatus("AIRBORNE");

        if (!route.getStatus().equals("IN_FLIGHT")) {
            routeService.updateRouteToInFlight(route);
        }

        aircraftService.updateFlightInfo(aircraft);


    }

    private Boolean checkIfIsInTheAirfield(Aircraft aircraft, Airfield destination) {

        return destination.getLocation().contains(aircraft.getPosition());

    }


}
