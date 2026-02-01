package com.asterisk.golink.application.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asterisk.golink.application.service.mapper.RouteMapper;
import com.asterisk.golink.domain.exception.DataNotFoundException;
import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.repository.jpa.JpaRouteEntityRepository;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;

class DefaultRouteServiceTest {

  private final JpaRouteEntityRepository repository =
      Mockito.mock(JpaRouteEntityRepository.class);

  private final RouteMapper mapper = Mappers.getMapper(RouteMapper.class);

  private DefaultRouteService service;

  @BeforeEach
  void setUp() {

    this.service = new DefaultRouteService(this.repository, this.mapper);
  }

  @Test
  void findAll() {

    RouteEntity route = new RouteEntity();
    route.setId(1L);

    Mockito.when(this.repository.findAll()).thenReturn(List.of(route));

    this.service.findAll();

    Mockito.verify(this.repository, Mockito.times(1)).findAll();
  }

  @Test
  void findByAircraftId() {

    UUID aircraftId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    RouteEntity route = new RouteEntity();
    route.setId(1L);

    Mockito.when(this.repository.findByAircraftId(aircraftId))
        .thenReturn(Optional.of(route));

    Route result = this.service.findByAircraftId(aircraftId);

    Mockito.verify(this.repository, Mockito.times(1))
        .findByAircraftId(aircraftId);

    Assertions.assertEquals(route.getId(), result.getId());
  }

  @Test
  void findByAircraftIdShouldThrowAnException() {

    UUID aircraftId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    Mockito.when(this.repository.findByAircraftId(aircraftId))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(DataNotFoundException.class,
        () -> this.service.findByAircraftId(aircraftId));
  }

  @Test
  void updateRouteToCompleted() {

    Route route = new Route();
    route.setId(1L);
    route.setArrivalTime(Timestamp.from(Instant.now()));
    route.setDepartureTime(Timestamp.from(Instant.now().minusSeconds(3600)));

    this.service.updateRouteToCompleted(route);

    Mockito.verify(this.repository, Mockito.times(1))
        .save(Mockito.argThat(r -> r.getId().equals(route.getId())
            && r.getStatus().equals("COMPLETED") && r.getDuration() == 60));
  }

  @Test
  void updateRouteToLanding() {

    Route route = new Route();
    route.setId(1L);

    this.service.updateRouteToLanding(route);

    Mockito.verify(this.repository, Mockito.times(1))
        .save(Mockito.argThat(r -> r.getId().equals(route.getId())
            && r.getStatus().equals("LANDING")));
  }

  @Test
  void updateRouteToDeparting() {

    Instant now = Instant.now();

    Route route = new Route();
    route.setId(1L);

    try (MockedStatic<Instant> instant = Mockito.mockStatic(Instant.class)) {
      instant.when(Instant::now).thenReturn(now);

      this.service.updateRouteToDeparting(route);
    }

    Mockito.verify(this.repository, Mockito.times(1))
        .save(Mockito.argThat(r -> r.getId().equals(route.getId())
            && r.getStatus().equals("DEPARTING")
            && r.getDepartureTime().equals(Timestamp.from(now))));
  }

  @Test
  void updateRouteToInFlight() {

    Route route = new Route();
    route.setId(1L);

    this.service.updateRouteToInFlight(route);

    Mockito.verify(this.repository, Mockito.times(1))
        .save(Mockito.argThat(r -> r.getId().equals(route.getId())
            && r.getStatus().equals("IN_FLIGHT")));
  }
}