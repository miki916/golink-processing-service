package com.asterisk.golink.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Route {

    private Long id;

    private Airfield origin;

    private Airfield destination;

    private Aircraft aircraft;

    private Timestamp departureTime;

    private Timestamp expectedDepartureTime;

    private Timestamp arrivalTime;

    private Timestamp expectedArrivalTime;

    private Integer duration;

    private String status; // IN_FLIGHT, LANDING, CANCELLED, DEPARTING, SCHEDULED, COMPLETED

}
