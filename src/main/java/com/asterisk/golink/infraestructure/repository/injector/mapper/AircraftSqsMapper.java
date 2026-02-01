package com.asterisk.golink.infraestructure.repository.injector.mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.injector.response.AircraftSqsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftSqsMapper {


    Aircraft toDomain(AircraftSqsResponse aircraftData);
}
