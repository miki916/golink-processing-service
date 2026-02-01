package com.asterisk.golink.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

@Getter
@Setter
public class Airfield {

    private UUID id;

    private String name;

    private Polygon location;

    private String country;
}
