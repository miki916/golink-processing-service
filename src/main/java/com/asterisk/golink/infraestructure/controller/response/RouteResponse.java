package com.asterisk.golink.infraestructure.controller.response;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponse {

  private Long id;

  private AirfieldResponse origin;

  private AirfieldResponse destination;

  private AircraftResponse aircraft;

  private Timestamp departureTime;

  private Timestamp expectedDepartureTime;

  private Timestamp arrivalTime;

  private Timestamp expectedArrivalTime;

  private Integer duration;

  private String status;

}
