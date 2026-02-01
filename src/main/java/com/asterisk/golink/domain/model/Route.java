package com.asterisk.golink.domain.model;

import java.sql.Timestamp;

import org.locationtech.jts.geom.Point;

import com.asterisk.golink.domain.model.modelEnum.RouteStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {

  private Long id;

  private Airfield origin;

  private Airfield destination;

  private Aircraft aircraft;

  private Timestamp departureTime;

  private Timestamp expectedDepartureTime;

  private Timestamp arrivalTime;

  private Timestamp expectedArrivalTime;

  private Integer duration;

  private RouteStatusEnum status; // IN_FLIGHT, LANDING, CANCELLED, DEPARTING, SCHEDULED, COMPLETED

  public void calculateEstimateArrivalTime(Point position) {

    double distanceToDestination = position.distance(destination.getLocation());

    double estimatedTimeInHours = distanceToDestination / aircraft.getSpeed();

    long estimatedTimeInMillis = (long) (estimatedTimeInHours * 3600000);

    this.expectedArrivalTime =
        new Timestamp(System.currentTimeMillis() + estimatedTimeInMillis);
  }
}
