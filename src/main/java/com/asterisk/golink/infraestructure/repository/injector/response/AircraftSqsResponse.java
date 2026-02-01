package com.asterisk.golink.infraestructure.repository.injector.response;

import java.util.UUID;

import org.locationtech.jts.geom.Point;

import com.asterisk.golink.infraestructure.serialization.PointDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AircraftSqsResponse {

  private UUID vin;

  @JsonDeserialize(using = PointDeserializer.class)
  private Point position;

  private String orientation;

  private Long speed;

  private Long altitude;

}
