package com.asterisk.golink.infraestructure.repository.jpa.entity;

import java.util.UUID;

import org.locationtech.jts.geom.Polygon;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "airfield",
    schema = "golink"
)
public class AirfieldEntity {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private Polygon location;

  @Column(name = "country")
  private String country;
}
