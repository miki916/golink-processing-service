package com.asterisk.golink.infraestructure.controller.response;

import java.util.UUID;

import org.locationtech.jts.geom.Point;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AircraftResponse {

  private UUID vin;

  private String model;

  private String company;

  private String status;

  private String flightStatus;

  private Point position;

  private String orientation;

  private Long speed;

  private Long altitude;

}
