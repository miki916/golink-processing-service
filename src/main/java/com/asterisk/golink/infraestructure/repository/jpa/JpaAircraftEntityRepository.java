package com.asterisk.golink.infraestructure.repository.jpa;

import com.asterisk.golink.infraestructure.repository.jpa.entity.AircraftEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAircraftEntityRepository extends JpaRepository<AircraftEntity, UUID> {


    @Modifying
    @Query("""
                    UPDATE AircraftEntity a SET
                        a.altitude = :altitude,
                        a.speed = :speed,
                        a.flightStatus = :flightStatus,
                        a.position = :position,
                        a.orientation = :orientation
                    WHERE a.vin = :vin
            """)
    void updateFlightInfo(UUID vin, Long altitude, Long speed, String flightStatus, Point position, String orientation);
}
