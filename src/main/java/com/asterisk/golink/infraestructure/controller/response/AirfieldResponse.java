package com.asterisk.golink.infraestructure.controller.response;

import java.util.UUID;

import org.locationtech.jts.geom.Polygon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirfieldResponse {

  private UUID id;

  private String name;

  private Polygon location;

  private String country;
}
