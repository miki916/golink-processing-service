package com.asterisk.golink.domain.model;

import java.util.UUID;

import org.locationtech.jts.geom.Point;

import com.asterisk.golink.domain.model.modelEnum.FlightStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aircraft {

  private UUID vin;

  private String model;

  private String company;

  private String status;

  private FlightStatusEnum flightStatus;

  private Point position;

  private String orientation;

  private Long speed;

  private Long altitude;

}
