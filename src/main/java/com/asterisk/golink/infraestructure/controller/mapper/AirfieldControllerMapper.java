package com.asterisk.golink.infraestructure.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.infraestructure.controller.response.AirfieldResponse;

@Mapper(componentModel = "spring")
public interface AirfieldControllerMapper {

  AirfieldResponse toResponse(Airfield domain);

  List<AirfieldResponse> toResponseList(List<Airfield> domainList);
}
