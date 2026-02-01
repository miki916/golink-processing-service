package com.asterisk.golink.infraestructure.controller.mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.controller.response.AircraftResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftControllerMapper {

    AircraftResponse toResponse(Aircraft domain);

    List<AircraftResponse> toResponseList(List<Aircraft> domainList);
}
