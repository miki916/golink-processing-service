package com.asterisk.golink.application.service;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import com.asterisk.golink.application.service.mapper.AircraftMapper;
import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.domain.model.modelEnum.FlightStatusEnum;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAircraftEntityRepository;

class DefaultAircraftServiceTest {

  private final JpaAircraftEntityRepository repository =
      Mockito.mock(JpaAircraftEntityRepository.class);

  private final AircraftMapper mapper = Mappers.getMapper(AircraftMapper.class);

  private DefaultAircraftService service;

  @BeforeEach
  void setUp() {

    this.service = new DefaultAircraftService(this.repository, this.mapper);
  }

  @Test
  void findAll() {

    this.service.findAll();
    Mockito.verify(this.repository, Mockito.times(1)).findAll();
  }

  @Test
  void updateFlightInfo() {

    UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    GeometryFactory geometryFactory = new GeometryFactory();
    Point position =
        geometryFactory.createPoint(new Coordinate(-118.4085, 33.9416));

    Aircraft aircraft = new Aircraft();
    aircraft.setVin(id);
    aircraft.setAltitude(1000L);
    aircraft.setSpeed(250L);
    aircraft.setFlightStatus(FlightStatusEnum.AIRBORNE);
    aircraft.setPosition(position);
    aircraft.setOrientation("NORTH");

    this.service.updateFlightInfo(aircraft);

    Mockito.verify(this.repository, Mockito.times(1))
        .updateFlightInfo(id, 1000L, 250L, "AIRBORNE", position, "NORTH");
  }
}