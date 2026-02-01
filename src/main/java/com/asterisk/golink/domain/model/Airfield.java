package com.asterisk.golink.domain.model;

import java.util.UUID;

import org.locationtech.jts.geom.Polygon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Airfield {

  private UUID id;

  private String name;

  private Polygon location;

  private String country;
}
