package com.asterisk.golink.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

@Getter
@Setter
public class Aircraft {

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
