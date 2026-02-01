package com.asterisk.golink.infraestructure.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;

@Repository
public interface JpaRouteEntityRepository
    extends JpaRepository<RouteEntity, Long> {

  @Query(
    "SELECT r FROM RouteEntity r WHERE r.aircraft.vin = :vin and r.arrivalTime IS NULL"
  )
  Optional<RouteEntity> findByAircraftId(UUID vin);
}
