package com.asterisk.golink.infraestructure.repository.injector.mapper;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.injector.response.AircraftSqsResponse;

@Mapper(componentModel = "spring")
public interface AircraftSqsMapper {

  Aircraft toDomain(AircraftSqsResponse aircraftData);
}
