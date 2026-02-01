package com.asterisk.golink.infraestructure.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.controller.response.AircraftResponse;

@Mapper(componentModel = "spring")
public interface AircraftControllerMapper {

  AircraftResponse toResponse(Aircraft domain);

  List<AircraftResponse> toResponseList(List<Aircraft> domainList);
}
