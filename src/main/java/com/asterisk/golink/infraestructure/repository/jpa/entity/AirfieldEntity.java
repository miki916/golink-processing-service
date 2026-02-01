package com.asterisk.golink.infraestructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Polygon;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "airfield", schema = "golink")
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
