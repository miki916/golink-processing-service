package com.asterisk.golink.domain.model;

import com.asterisk.golink.domain.model.modelEnum.FlightStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Getter
@Setter
public class Aircraft {

    private UUID vin;

    private String model;

    private String company;

    private String status;

    private FlightStatusEnum flightStatus;

    private Point position;

    private String orientation;

    private Long speed;

    private Long altitude;

}
