package com.asterisk.golink.infraestructure.controller.response;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

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
