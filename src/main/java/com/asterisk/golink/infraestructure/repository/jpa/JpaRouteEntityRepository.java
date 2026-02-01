package com.asterisk.golink.infraestructure.repository.jpa;

import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaRouteEntityRepository extends JpaRepository<RouteEntity, Long> {

    @Query("SELECT r FROM RouteEntity r WHERE r.aircraft.vin = :vin and r.arrivalTime IS NULL")
    RouteEntity findByAircraftId(UUID vin);
}
