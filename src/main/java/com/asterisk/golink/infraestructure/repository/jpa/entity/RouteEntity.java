package com.asterisk.golink.infraestructure.repository.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "rute", schema = "golink")
public class RouteEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "origin")
    private AirfieldEntity origin;

    @ManyToOne()
    @JoinColumn(name = "destination")
    private AirfieldEntity destination;

    @ManyToOne()
    @JoinColumn(name = "aircraft_vin")
    private AircraftEntity aircraft;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "expected_departure_time")
    private Timestamp expectedDepartureTime;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "expected_arrival_time")
    private Timestamp expectedArrivalTime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    private String status;

}
