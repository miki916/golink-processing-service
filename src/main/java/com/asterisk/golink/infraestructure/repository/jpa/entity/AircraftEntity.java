package com.asterisk.golink.infraestructure.repository.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "aircraft", schema = "golink")
public class AircraftEntity {

    @Id
    @Column(name = "vin")
    private UUID vin;

    @Column(name = "model")
    private String model;

    @Column(name = "company")
    private String company;

    @Column(name = "status")
    private String status; // OPERATIVE, MAINTENANCE, RETIRED

    @Column(name = "flight_status")
    private String flightStatus; // ON_GROUND, AIRBORNE

    @Column(name = "position")
    private Point position;

    @Column(name = "orientation")
    private String orientation;

    @Column(name = "speed")
    private long speed;

    @Column(name = "altitude")
    private long altitude;

}
