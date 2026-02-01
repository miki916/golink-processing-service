package com.asterisk.golink.infraestructure.repository.injecter.mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.injecter.response.AircraftSqsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AircraftSqsMapper {


    Aircraft toDomain(AircraftSqsResponse aircraftData);
}
