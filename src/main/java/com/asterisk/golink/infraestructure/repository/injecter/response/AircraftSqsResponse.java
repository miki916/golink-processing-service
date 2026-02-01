package com.asterisk.golink.infraestructure.repository.injecter.response;

import com.asterisk.golink.infraestructure.serialization.PointDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Getter
@Setter
public class AircraftSqsResponse {

    private UUID vin;

    @JsonDeserialize(using = PointDeserializer.class)
    private Point position;

    private String orientation;

    private Long speed;

    private Long altitude;

}
